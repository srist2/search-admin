package com.gxstnu.search.repository;

import com.gxstnu.search.entity.missPerson.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    public List<Claim> findAll();

    // 根据claimId更新isPass
    @Modifying
    @Transactional
    @Query(value = "update miss_person_claim set isPass = ?1 where claimId = ?2")
    public int updateIsPass(Integer isPass, Integer claimId);
}
