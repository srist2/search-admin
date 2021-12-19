package com.gxstnu.search.service.impl;

import com.gxstnu.search.entity.dict.MissTypeDict;
import com.gxstnu.search.entity.dict.SeekTypeDict;
import com.gxstnu.search.entity.dict.SexDict;
import com.gxstnu.search.repository.dict.MissTypeDictRepository;
import com.gxstnu.search.repository.dict.SeekTypeDictRepository;
import com.gxstnu.search.repository.dict.SexDictRepository;
import com.gxstnu.search.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private MissTypeDictRepository missTypeDictRepository;
    @Autowired
    private SeekTypeDictRepository seekTypeDictRepository;
    @Autowired
    private SexDictRepository sexDictRepository;

    @Override
    public List<MissTypeDict> findAllMissTypeDict() {
        return missTypeDictRepository.findAll();
    }

    @Override
    public MissTypeDict findByIdMissTypeDict(Integer id) {
        //SpringBoot2.0 (Spring-Data-Jpa) findById(findOne())和Optional<T>的使用
        // https://www.cjavapy.com/article/293/
        return missTypeDictRepository.findById(id).orElse(new MissTypeDict());
    }

    @Override
    public List<SeekTypeDict> findAllSeekTypeDict() {
        return seekTypeDictRepository.findAll();
    }

    @Override
    public List<SexDict> findAllSexDict() {
        return sexDictRepository.findAll();
    }
}
