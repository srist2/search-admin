package com.gxstnu.search.service;

import com.gxstnu.search.entity.missPerson.Claim;

import java.util.List;


public interface ClaimService {
    /**
     * 查询所有
     */
    public List<Claim> findAll();

    /**
     * 寻人认领审核
     * @param isPass 1: 通过 2:不通过
     * @param claimId 认领人Id
     * @return {Integer} 0:失败 1:成功
     */
    public int updateIsPass(Integer isPass, Integer claimId);

    public Claim save(Claim claim);
}
