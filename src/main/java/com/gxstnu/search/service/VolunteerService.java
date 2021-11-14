package com.gxstnu.search.service;

import com.gxstnu.search.entity.Volunteer;

import java.util.List;
import java.util.Map;

public interface VolunteerService {
    // 查询所有
    public List<Volunteer> findAll();
    // 志愿者详细信息查询
    public List<Map<String,Object>> findVolunteerList();
    // 更新志愿者信息
    public int saveVolunteerByClass(Volunteer volunteer);
    // 删除志愿者信息
    public void deleteByVtUserId(Integer id);
    // 添加志愿者信息
    public Volunteer save(Volunteer volunteer);
    // 根据 vt_user_id查询
    public List<Volunteer> findByVtUserId(Integer id);
}
