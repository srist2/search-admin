package com.gxstnu.search.service;
import java.util.Map;

public interface NavigationService {
    /**
     * 获取导航栏
     * @return
     */
    public Map<String,Object> findMenu();
}