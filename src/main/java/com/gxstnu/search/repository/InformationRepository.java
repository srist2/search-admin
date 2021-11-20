package com.gxstnu.search.repository;

import com.gxstnu.search.entity.missPerson.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface InformationRepository extends JpaRepository<Information, Integer> {

    // 根据id查询
    @Modifying
    @Transactional
    @Query(value = "select ifm from miss_person_information as ifm where ifm.infoId=?1")
    public List<Information> findByInfoId(Integer id);

    // 根据info_id更新外键ct_by_id
    @Modifying
    @Transactional
    @Query(value = "update miss_person_information set ct_by_id = ?1 where info_id = ?2", nativeQuery = true)
    public int updateCtById(Integer ctId, Integer infoId);

    // 根据info_id删除
    @Modifying
    @Transactional
    @Query(value = "delete miss_person_information where infoId = ?1")
    public int deleteByInfoId(Integer id);

    // 根据info_id更新info_is_show
    @Modifying
    @Transactional
    @Query(value = "update miss_person_information set infoIsShow = ?1 where infoId = ?2")
    public int updateIsShowById(Integer isShow, Integer infoId);

    // 查询展示的失踪者信息
    @Query(value = "select ifm from miss_person_information as ifm where ifm.infoIsShow=?1")
    public List<Information> findAllByIsShow(Integer isShow);
}
