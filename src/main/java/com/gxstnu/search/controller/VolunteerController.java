package com.gxstnu.search.controller;

import com.gxstnu.search.entity.User;
import com.gxstnu.search.entity.Vo.VolunteerAndUserVo;
import com.gxstnu.search.entity.Volunteer;
import com.gxstnu.search.service.UserService;
import com.gxstnu.search.service.VolunteerService;
import com.gxstnu.search.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    VolunteerService volunteerService;
    @Autowired
    UserService userService;

    /**
     * 查询所有
     */
    @GetMapping("/findAll")
    public List<Volunteer> findAll() {
        return volunteerService.findAll();
    }

    /**
     * 查询所有
     */
    @GetMapping("/findVolunteerList")
    public List<Map<String,Object>> findVolunteerList() {
        return volunteerService.findVolunteerList();
    }

    /**
     * 更新志愿者列表
     */
    @PostMapping("/updateVolunteerList")
    public Result updateVolunteerList(@RequestBody VolunteerAndUserVo listVo) {
        // 创建志愿者对象
        Volunteer volunteer = new Volunteer();
        volunteer.setVolunteerId(listVo.getVolunteerId());
        volunteer.setIdCard(listVo.getIdCard());
        volunteer.setResidentLocation(listVo.getResidentLocation());
        volunteer.setAddress(listVo.getAddress());
        volunteer.setZipCode(listVo.getZipCode());
        volunteer.setProfession(listVo.getProfession());

        // 创建用户对象
        User user = new User();
        user.setUserId(listVo.getUserId());
        user.setUserName(listVo.getUserName());
        user.setNickName(listVo.getNickName());
        user.setPhone(listVo.getPhone());
        user.setEmail(listVo.getEmail());
        // 调用更新方法
        userService.saveUserByClass(user);
        volunteerService.saveVolunteerByClass(volunteer);
        return Result.success(1);
    }

    /**
     * 删除志愿者信息
     */
    @PostMapping("/deleteVolunteer")
    public Result deleteVolunteer(@RequestBody Map params) {
        Integer userId = (Integer) params.get("userId");
        // 按照user_id删除user表和volunteer表中数据
        volunteerService.deleteByVtUserId(userId);
        userService.deleteByUserId(userId);
        return Result.success(1);
    }

    /**
     * 增加志愿者信息
     */
    @PostMapping("addVolunteer")
    public Result addVolunteer(@RequestBody VolunteerAndUserVo listVo) {
        // 创建用户对象
        User user = new User();
        user.setUserName(listVo.getUserName());
        user.setPassword(listVo.getPassword());
        user.setNickName(listVo.getNickName());
        user.setPhone(listVo.getPhone());
        user.setEmail(listVo.getEmail());
        user.setRemark(listVo.getRemark());
        user.setStatus(listVo.getStatus());
        user.setRole(listVo.getRole());
        User user1 = userService.save(user);
        // 创建志愿者对象
        Volunteer volunteer = new Volunteer();
        volunteer.setRole(listVo.getRole());
        volunteer.setIdCard(listVo.getIdCard());
        volunteer.setResidentLocation(listVo.getResidentLocation());
        volunteer.setAddress(listVo.getAddress());
        volunteer.setZipCode(listVo.getZipCode());
        volunteer.setProfession(listVo.getProfession());
        volunteer.setVtUserId(user1.getUserId());

        Volunteer volunteer1 = volunteerService.save(volunteer);
        return Result.success(1);
    }
}
