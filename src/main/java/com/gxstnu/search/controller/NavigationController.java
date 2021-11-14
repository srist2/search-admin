package com.gxstnu.search.controller;

import com.gxstnu.search.service.NavigationService;
import com.gxstnu.search.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/navigation")
public class NavigationController {

    @Autowired
    NavigationService navigationService;

    @GetMapping("/findMenu")
    @ResponseBody
    public Result findMenu(){
        Map<String, Object> data = navigationService.findMenu();
        return Result.success(data);
    }

}