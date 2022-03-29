package com.gxstnu.search.service;

import com.gxstnu.search.entity.Vo.DateVo;
import com.gxstnu.search.entity.Vo.MissType;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public interface DateService {
    // 获取认领人数量
    public int getContactNumber();
    // 获取联系人数量
    public int getClaimNumber();

    public List<Integer> getMissTypeNumber();

    public List<MissType> getMissTypeNumberMan(Integer sexType);

    public int getMissManCount(Integer sexType);

    public List<MissType> getSeekTypeNumberMan();
}
