package com.gxstnu.search.service.impl;

import com.gxstnu.search.entity.Volunteer;
import com.gxstnu.search.entity.missPerson.Information;
import com.gxstnu.search.repository.InformationRepository;
import com.gxstnu.search.service.InformationService;
import com.gxstnu.search.utils.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationRepository informationRepository;

    /**
     * 查询所有失踪者信息
     * @return Information
     */
    @Override
    public List<Information> findAll() {
        return informationRepository.findAll();
    }

    /**
     * 添加/更新失踪信息
     * @return Information
     */
    @Override
    public Information save(Information information) {
        return informationRepository.save(information);
    }

    @Override
    public int saveByClass(Information information) {
        int flag = 0;
        if (information != null && information.getInfoId() != null) {
            Information info = informationRepository.getOne(information.getInfoId());
            if (info != null) {
                //将apply中非空的值赋给apply1中,并将apply1重新存储
                JpaUtil.copyNotNullProperties(information, info);
            }
            flag = informationRepository.save(info).getInfoId();
        }
        return flag;
    }

    /**
     * 更新志愿者ct_by_id 外键
     * @param ctId  联系人ct_id
     * @param infoId 失踪者信息id
     * @return  {Integer} 0 1
     */
    @Override
    public int updateCtById(Integer ctId, Integer infoId) {
        return informationRepository.updateCtById(ctId, infoId);
    }

    /**
     * 删除失踪者信息
     * @param id  失踪者id
     * @return {Integer} 0 1
     */
    @Override
    public int deleteByInfoId(Integer id) {
        return informationRepository.deleteByInfoId(id);
    }

    /**
     * 更新失踪者信息是否发布
     * @param isShow    是否发表
     * @param infoId    失踪者信息id
     * @return {Integer} 0 1
     */
    @Override
    public int updateIsShowById(Integer isShow, Integer infoId) {
        return informationRepository.updateIsShowById(isShow, infoId);
    }
}
