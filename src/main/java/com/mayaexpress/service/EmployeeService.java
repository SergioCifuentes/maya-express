package com.mayaexpress.service;

import com.mayaexpress.entity.Employee;
import com.mayaexpress.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void create(Employee employee) {
        employeeRepository.save(employee);
    }
}
