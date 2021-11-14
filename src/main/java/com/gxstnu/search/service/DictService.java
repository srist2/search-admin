package com.gxstnu.search.service;

import com.gxstnu.search.entity.dict.MissTypeDict;
import com.gxstnu.search.entity.dict.SeekTypeDict;
import com.gxstnu.search.entity.dict.SexDict;

import java.util.List;
import java.util.Optional;

public interface DictService {
    /**
     * 查询全部失踪类型字典
     * @return {List} MissTypeDict
     */
    public List<MissTypeDict> findAllMissTypeDict();

    /**
     * 根据id查询失踪类型字典
     * @param id  失踪者Id
     * @return {Object} MissTypeDict
     */
    public MissTypeDict findByIdMissTypeDict(Integer id);

    /**
     * 查询全部寻找类型字典
     * @return  {List} SeekTypeDict
     */
    public List<SeekTypeDict> findAllSeekTypeDict();

    /**
     * 查询性别字典
     * @return  {List} SexDict
     */
    public List<SexDict> findAllSexDict();
}
