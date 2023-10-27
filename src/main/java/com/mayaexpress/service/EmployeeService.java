package com.mayaexpress.service;

import com.mayaexpress.entity.Employee;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.exception.EmployeeNotFoundException;
import com.mayaexpress.repository.EmployeeRepository;
import com.mayaexpress.util.MergeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private MergeEntity<Employee> merge;

    private final PasswordEncoder encoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
        this.merge = new MergeEntity<>();
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
}
