package com.mayaexpress.service;

import com.mayaexpress.entity.Employee;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder encoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.encoder=encoder;
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
}
