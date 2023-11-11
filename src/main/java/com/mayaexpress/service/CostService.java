package com.mayaexpress.service;

import com.mayaexpress.entity.Branch;
import com.mayaexpress.entity.Cost;
import com.mayaexpress.entity.CostType;
import com.mayaexpress.entity.Warehouse;
import com.mayaexpress.exception.ResourceNotFoundException;
import com.mayaexpress.repository.BranchRepository;
import com.mayaexpress.repository.CostRepository;
import com.mayaexpress.repository.CostTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostService {

    private final CostRepository costRepository;

    private final CostTypeRepository costTypeRepository;

    private final BranchRepository branchRepository;

    public CostService(CostRepository costRepository, CostTypeRepository costTypeRepository, BranchRepository branchRepository) {
        this.costRepository = costRepository;
        this.costTypeRepository = costTypeRepository;
        this.branchRepository = branchRepository;
    }

    public Page<CostType> getAllCostType(int page, int size, boolean pagination) {
        return  costTypeRepository.findAll(pagination ? PageRequest.of(page,size) : Pageable.unpaged());
    }

    public Cost addCost(Cost cost) {
        Optional<CostType> costType = costTypeRepository.findById(cost.getCostType().getId());
        if(costType.isEmpty()) throw new ResourceNotFoundException("CostType", "ID", cost.getCostType().getId());
        Optional<Branch> branch = branchRepository.findById(cost.getBranch().getId());
        if(branch.isEmpty()) throw new ResourceNotFoundException("Branch", "ID", cost.getCostType().getId());
        return costRepository.save(cost);
    }
}
