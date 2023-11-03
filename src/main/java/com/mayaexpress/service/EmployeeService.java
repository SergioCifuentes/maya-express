package com.mayaexpress.service;

import com.mayaexpress.entity.*;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.exception.EmployeeNotFoundException;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.EmployeeRepository;
import com.mayaexpress.repository.PositionRepository;
import com.mayaexpress.repository.WageRepository;
import com.mayaexpress.util.MergeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchService branchService;
    private final MergeEntity<Employee> merge;

    private final PasswordEncoder encoder;

    private final PositionRepository positionRepository;

    private final WageRepository wageRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder encoder,
                           BranchService branchService, PositionRepository positionRepository, WageRepository wageRepository) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
        this.merge = new MergeEntity<>();
        this.branchService = branchService;
        this.positionRepository=positionRepository;
        this.wageRepository=wageRepository;

    }

    public void create(Employee employee) {
        if(employee.getId()!=null){
            Optional<Employee> employeeOptional= employeeRepository.findById(employee.getId());
            if(employeeOptional.isPresent()) throw new APIException(HttpStatus.CONFLICT,"ID Already Exists");
        }
        Optional<Employee> employeeOptional= employeeRepository.findByUsername(employee.getUsername());
        if(employeeOptional.isPresent()) throw new APIException(HttpStatus.CONFLICT,"Username Already Exists");
        employee.setPassword(encoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }

    public Page<Employee> getAll(int page, int size, boolean pagination) {
        return  employeeRepository.findAll(pagination ? PageRequest.of(page,size) : Pageable.unpaged());
    }

    public Page<Employee> getAllActivates(int page, int size, boolean pagination){
        return employeeRepository.findAllByIsEnableIsTrue(pagination ? PageRequest.of(page,size) : Pageable.unpaged());
    }

    public Page<Employee> getAllDeactivates(int page, int size, boolean pagination){
        return employeeRepository.findAllByIsEnableIsFalse(pagination ? PageRequest.of(page,size) : Pageable.unpaged());
    }

    public Employee update(Employee employee, BigDecimal id) {
        Optional<Employee> employeeDB = employeeRepository.findById(id);
        if (employeeDB.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Employee not found.");
        }
        return employeeRepository.save(merge.mergeEntities(employee, employeeDB.get()));
    }

    public void delete(BigDecimal id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Employee not found.");
        }
        employee.get().setEnable(false);
        employeeRepository.save(employee.get());
    }

    public Employee get(BigDecimal id) {
        Optional<Employee> employeeDB = employeeRepository.findById(id);
        if (employeeDB.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Employee not found.");
        }
        return employeeDB.get();
    }

    public Employee assignEmployee(BigDecimal idEmployee, Integer idBranch) {
        Branch branch = branchService.get(idBranch);
        Employee employee = get(idEmployee);
        employee.setBranch(branch);
        return employeeRepository.save(employee);
    }

    public List<Position> getPositions(){
        return positionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Position getPosition(Integer id){
        Optional<Position> position = positionRepository.findById(id);
        if (position.isEmpty()) throw new ResourceNotFoundException("Position","id",id);
        return position.get();
    }

    public List<Wage> getWages(){
        return wageRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Wage getWage(Integer id){
        Optional<Wage> wage = wageRepository.findById(id);
        if (wage.isEmpty()) throw new ResourceNotFoundException("Wage","id",id);
        return wage.get();
    }

    public Wage getWageByPosition(Integer id){
        Optional<Position> position = positionRepository.findById(id);
        if (position.isEmpty()) throw new ResourceNotFoundException("Position","id",id);
        return position.get().getWage();
    }

}
