package com.gxstnu.search.controller;

import com.gxstnu.search.entity.Vo.DateVo;
import com.gxstnu.search.entity.Vo.MissType;
import com.gxstnu.search.entity.dict.MissTypeDict;
import com.gxstnu.search.service.DateService;
import com.gxstnu.search.service.DictService;
import com.gxstnu.search.service.UserService;
import com.gxstnu.search.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/date")
public class DateController {
    @Resource
    private UserService userService;
    @Resource
    private DateService dateService;
    @Resource
    private DictService dictService;

    @GetMapping("/getUserStructure")
    public Result getDate() {
        DateVo dateVo = userService.getUserTypeNumber();
        int contactNumber = dateService.getContactNumber();
        int claimNumber = dateService.getClaimNumber();
        dateVo.setContactNumber(contactNumber);
        dateVo.setClaimNumber(claimNumber);

        List<Integer> integers = dateService.getMissTypeNumber();
        // 失踪者男性数据
        List<MissType> missTypeNumberMan = dateService.getMissTypeNumberMan(1);
        dateVo.setMissManTypeLists(missTypeNumberMan);
        // 失踪者女性数据
        List<MissType> missTypeNumberWoman = dateService.getMissTypeNumberMan(2);
        dateVo.setMissWomanTypeLists(missTypeNumberWoman);
        int missManNumber = dateService.getMissManCount(1);
        int missWomanNumber = dateService.getMissManCount(2);
        dateVo.setMissManNumber(missManNumber);
        dateVo.setMissWomanNumber(missWomanNumber);

        List<MissType> missTypeList = dateService.getSeekTypeNumberMan();
        dateVo.setSeekTypeLists(missTypeList);

//        dateVo.setMissType(integers);
        return Result.success(dateVo);
    }
}
