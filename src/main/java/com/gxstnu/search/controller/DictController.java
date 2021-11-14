package com.gxstnu.search.controller;

import com.gxstnu.search.entity.dict.MissTypeDict;
import com.gxstnu.search.entity.dict.SeekTypeDict;
import com.gxstnu.search.service.DictService;
import com.gxstnu.search.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private DictService dictService;

    @GetMapping("/seekType")
    public Result findSeekTypeDict() {
        List<SeekTypeDict> seekTypeDictList = dictService.findAllSeekTypeDict();
        return Result.success(seekTypeDictList);
    }

    @GetMapping("/missType")
    public Result findMissTypeDict() {
        List<MissTypeDict> missTypeDictList = dictService.findAllMissTypeDict();
        return Result.success(missTypeDictList);
    }
}
