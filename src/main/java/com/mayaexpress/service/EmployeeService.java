package com.mayaexpress.service;

import com.mayaexpress.entity.Employee;
import com.mayaexpress.repository.EmployeeRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder encoder;

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.encoder=encoder;
    }

    public void create(Employee employee) {
        employee.setPassword(encoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }
}
