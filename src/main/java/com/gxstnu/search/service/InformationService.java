package com.gxstnu.search.service;

import com.gxstnu.search.entity.Volunteer;
import com.gxstnu.search.entity.missPerson.Information;

import java.util.List;

public interface InformationService {

    /**
     * 查询所有失踪者信息
     *
     * @return Information
     */
    public List<Information> findAll();

    /**
     * 添加/更新失踪信息
     *
     * @return Information
     */
    public Information save(Information information);

    /**
     * 当值为null也能用save()保存
     *
     * @param information 失踪者信息
     * @return {Integer} 0:失败 1:成功
     */
    public int saveByClass(Information information);

    /**
     * 更新志愿者ct_by_id 外键
     *
     * @param ctId   联系人ct_id
     * @param infoId 失踪者信息id
     * @return {Integer} 0:失败 1:成功
     */
    public int updateCtById(Integer ctId, Integer infoId);

    /**
     * 删除失踪者信息
     *
     * @param id 失踪者id
     * @return {Integer} 0:失败 1:成功
     */
    public int deleteByInfoId(Integer id);

    /**
     * 更新失踪者信息是否发布
     *
     * @param isShow 是否发表
     * @param infoId 失踪者信息id
     * @return {Integer} 0:失败 1:成功
     */
    public int updateIsShowById(Integer isShow, Integer infoId);

    /**
     * 查询展示的失踪者信息
     *
     * @param isShow 1:显示 2:不显示
     * @return {List} Information
     */
    public List<Information> findAllByIsShow(Integer isShow);

    /**
     * 根据Id查询
     * @param id
     * @return Information
     */
    public Information findAllByInfoId(Integer id);

    /**
     * 根据寻找类型和是否展示倒序查询
     * @return
     */
    public List<Information> findSeekTypeByIsShow(Integer seekType, Integer isShow);

    /**
     * 根据寻找类型(其他类型)和是否展示倒序查询
     * @return
     */
    public List<Information> findSeekOtherTypeByIsShow();

    public List<Information> findAllByInfoName(String infoName, Integer isShow);
}
