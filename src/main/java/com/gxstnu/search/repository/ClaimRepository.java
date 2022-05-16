package com.gxstnu.search.repository;

import com.gxstnu.search.entity.missPerson.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    public List<Claim> findAll();

    // 根据claimId更新isPass
    @Modifying
    @Transactional
    @Query(value = "update miss_person_claim set isPass = ?1 where claimId = ?2")
    public int updateIsPass(Integer isPass, Integer claimId);

    @Query(nativeQuery=true, value ="select * from miss_person_claim c where if(IFNULL(?1, '') !='',c.claim_name like concat('%',?1,'%'),1=1) and if(IFNULL(?2,'')!='',c.is_pass = ?2,1=1)  order by create_time DESC ")
    List<Claim> findByCode(String infoName, Integer isPass);
}
