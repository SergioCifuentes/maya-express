package com.mayaexpress.service;

import com.mayaexpress.entity.Branch;
import com.mayaexpress.entity.Employee;
import com.mayaexpress.exception.APIException;
import com.mayaexpress.repository.BranchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch get(Integer id) {
        Optional<Branch> branchDB = branchRepository.findById(id);
        if (branchDB.isEmpty()) {
            throw new APIException(HttpStatus.NOT_FOUND, "Branch not found.");
        }
        return branchDB.get();
    }
}
