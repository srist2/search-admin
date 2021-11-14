package com.gxstnu.search.service;

import com.gxstnu.search.entity.Volunteer;
import com.gxstnu.search.entity.missPerson.Information;

import java.util.List;

public interface InformationService {
    // 查询所有
    public List<Information> findAll();
    // 添加/更新
    public Information save(Information information);
    // 更新失踪者信息
    public int saveByClass(Information information);
    // 更新志愿者ctById 外键
    public int updateCtById(Integer ctId, Integer infoId);
    // 删除失踪者信息
    public int deleteByInfoId(Integer id);
    // 更新失踪者信息是否发布
    public int updateIsShowById(Integer isShow, Integer infoId);
}
