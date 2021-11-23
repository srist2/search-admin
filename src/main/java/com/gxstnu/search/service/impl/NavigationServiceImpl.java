package com.gxstnu.search.service.impl;

import com.gxstnu.search.entity.Navigation;
import com.gxstnu.search.repository.NavigationRepository;
import com.gxstnu.search.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    NavigationRepository navigationRepository;

    @Cacheable(cacheNames = "navigation", unless = "#result == null")
    @Override
    public Map<String, Object> findMenu() {
        Map<String,Object> data = new HashMap<>();
//        //按照pid获取到根目录进行存储对应的子目录
//        List<Navigation> navId = navigationRepository.getNavigationByPid();

        Integer id = 0;
        List<Navigation> navId = navigationRepository.findAllByPid(id);
        for(Navigation nav : navId){
            List<Navigation> navigationListByPId = navigationRepository.getNavigationListByPId(nav.getId());
            nav.setChildrens(navigationListByPId);
        }
        data.put("menu",navId);
        return data;
    }
}