package com.banking.service;

import com.banking.entity.Branch;
import com.banking.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    
    @Autowired
    private BranchRepository branchRepository;
    
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
    
    public Optional<Branch> getBranchById(Long id) {
        return branchRepository.findById(id);
    }
    
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }
    
    public Branch updateBranch(Long id, Branch branchDetails) {
        Optional<Branch> branch = branchRepository.findById(id);
        if (branch.isPresent()) {
            Branch existingBranch = branch.get();
            existingBranch.setName(branchDetails.getName());
            existingBranch.setAddress(branchDetails.getAddress());
            existingBranch.setPhone(branchDetails.getPhone());
            return branchRepository.save(existingBranch);
        }
        return null;
    }
    
    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }
}
