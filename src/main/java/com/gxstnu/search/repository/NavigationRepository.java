package com.gxstnu.search.repository;

import com.gxstnu.search.entity.Navigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface NavigationRepository extends JpaRepository<Navigation, Integer> {

    // 根据PId查询
    @Modifying
    @Transactional
    @Query(value = "SELECT nav FROM Navigation as nav WHERE nav.pid = ?1")
    List<Navigation> getNavigationListByPId(Integer pid);
    // 查询全部
    List<Navigation> findAllByPid(Integer id);
}