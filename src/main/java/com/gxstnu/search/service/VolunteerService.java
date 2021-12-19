package com.gxstnu.search.service;

import com.gxstnu.search.entity.Volunteer;

import java.util.List;
import java.util.Map;

public interface VolunteerService {

    /**
     * 查询所有
     * @return List<Volunteer>
     */
    public List<Volunteer> findAll();

    /**
     * 志愿者详细信息查询
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> findVolunteerList();

    /**
     * 更新志愿者信息
     * @param volunteer 志愿者类
     * @return 0:失败 1: 成功
     */
    public int saveVolunteerByClass(Volunteer volunteer);

    /**
     * 删除志愿者信息
     * @param id    志愿者Id
     */
    public void deleteByVtUserId(Integer id);

    /**
     * 添加志愿者信息
     * @param volunteer 志愿者类
     * @return  {Object} volunteer
     */
    public Volunteer save(Volunteer volunteer);

    /**
     * 根据 vt_user_id查询
     * @param id    vt_user_id
     * @return  List<Volunteer>
     */
    public List<Volunteer> findByVtUserId(Integer id);
}
