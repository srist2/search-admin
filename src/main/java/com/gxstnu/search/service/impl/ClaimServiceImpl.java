package com.gxstnu.search.service.impl;

import com.gxstnu.search.entity.missPerson.Claim;
import com.gxstnu.search.repository.ClaimRepository;
import com.gxstnu.search.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    // 查询所有
    @Override
    public List<Claim> findAll() {
        return claimRepository.findAll();
    }

    // 寻人认领审核
    @Override
    public int updateIsPass(Integer isPass, Integer claimId) {
        return claimRepository.updateIsPass(isPass, claimId);
    }

    @Override
    public Claim save(Claim claim) {
        return claimRepository.save(claim);
    }
}
