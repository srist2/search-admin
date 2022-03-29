package com.gxstnu.search.service.impl;

import com.gxstnu.search.entity.Vo.MissType;
import com.gxstnu.search.repository.DateRepository;
import com.gxstnu.search.service.DateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DateServiceImpl implements DateService {
    @Resource
    private DateRepository dateRepository;

    @Override
    public int getContactNumber() {
//        DateVo dateVo = new DateVo();
//        dateVo.setContactNumber();
        return dateRepository.getContactNumber();
    }

    @Override
    public int getClaimNumber() {
        return dateRepository.getClaimNumber();
    }

    @Override
    public List<Integer> getMissTypeNumber() {
        return dateRepository.getMissTypeNumber();
    }

    @Override
    public List<MissType> getMissTypeNumberMan(Integer sexType) {
        List<Map<String, Object>> mapList = dateRepository.getMissTypeNumberMan(sexType);
        List<MissType> missTypeList = new ArrayList<>();
        for(Map<String, Object> item : mapList) {
            MissType missType = new MissType();
            missType.setMissName(item.get("missType").toString());
            missType.setSexNumber(item.get("sexNumber").toString());
            missTypeList.add(missType);
        }
        return missTypeList;
    }

    @Override
    public int getMissManCount(Integer sexType) {
        return dateRepository.getMissManCount(sexType);
    }

    @Override
    public List<MissType> getSeekTypeNumberMan() {
        List<Map<String, Object>> mapList = dateRepository.getSeekTypeNumberMan(1);
        List<MissType> missTypeList = new ArrayList<>();
        for(Map<String, Object> item : mapList) {
            MissType missType = new MissType();
            missType.setMissName(item.get("seekType").toString());
            missType.setSexNumber(item.get("sexNumber").toString());
            missTypeList.add(missType);
        }
        return missTypeList;
    }
}
