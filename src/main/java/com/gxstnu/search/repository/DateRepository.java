package com.gxstnu.search.repository;

import com.gxstnu.search.entity.User;
import com.gxstnu.search.entity.Vo.DateVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Array;
import java.util.List;
import java.util.Map;

public interface DateRepository extends JpaRepository<User, Integer> {
    // 获取认领人数量
    @Query(value = "select count(*) from miss_person_claim", nativeQuery = true)
    public int getClaimNumber();
    // 获取联系人数量
    @Query(value = "select count(*) from miss_person_contact", nativeQuery = true)
    public int getContactNumber();

    @Query(value = "select info_miss_type from miss_person_information", nativeQuery = true)
    public List<Integer> getMissTypeNumber();

    // 获取失踪类型数据
    @Query(value = "select info_miss_type as missType, info_sex as sexNumber from miss_person_information where info_sex = ?1", nativeQuery = true)
    public List<Map<String, Object>> getMissTypeNumberMan(Integer sexType);

    // 获取失踪人数
    @Query(value = "select count(info_sex) as sexNumber from miss_person_information where info_sex = ?1", nativeQuery = true)
    public int getMissManCount(Integer sexType);

    // 获取寻找类型数据
    @Query(value = "select info_seek_type as seekType, info_sex as sexNumber from miss_person_information", nativeQuery = true)
    public List<Map<String, Object>> getSeekTypeNumberMan(Integer sexType);
}
