package com.gxstnu.search.controller;

import com.gxstnu.search.entity.User;
import com.gxstnu.search.entity.Vo.VolunteerAndUserVo;
import com.gxstnu.search.entity.Volunteer;
import com.gxstnu.search.service.UserService;
import com.gxstnu.search.service.VolunteerService;
import com.gxstnu.search.utils.Result;
import com.gxstnu.search.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private VolunteerService volunteerService;

    /**
     * 用户登录
     *
     * @param user 用户模型
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        System.out.println("user" + user);
        // 判断账号是否存在
        int flag = userService.findByUserName(user.getUserName());
        if (flag == 0) {
            return Result.fail(ResultCode.USER_NOT_EXISTS.getMessage());
        }
        // 判断账号密码是否正确
        User user1 = userService.login(user.getUserName(), user.getPassword());
        if (user1.getUserName() == null || user1.getPassword() == null) {
            return Result.fail(ResultCode.USER_LOGIN_ERROR.getMessage());
        }
        // 账号已禁用
        if (user1.getStatus() == 2) {
            return Result.fail(ResultCode.USER_ACCOUNT_FORBIDDEN.getMessage());
        }
        return Result.success(ResultCode.USER_LOGIN_SUCCESS.getMessage());
    }

    /**
     * 用户注册
     *
     * @param vu 用户和志愿者模型
     * @return Result
     */
    @PostMapping("/register")
    public Result register(@RequestBody VolunteerAndUserVo vu) {
        // 插入成功判断
        Boolean uFlag = false, vFlag = false;
        // 判断账号名是否注册
        int flag = userService.findByUserName(vu.getUserName());
        if (flag > 0) {
            return Result.fail(ResultCode.USER_HAS_EXISTED.getMessage());
        }
        // 用户数据插入
        User user = new User();
        user.setUserName(vu.getUserName());
        user.setPassword(vu.getPassword());
        user.setNickName(vu.getNickName());
        user.setPhone(vu.getPhone());
        user.setEmail(vu.getEmail());
        user.setRemark(vu.getRemark());
        user.setStatus(vu.getStatus());
        user.setRole(vu.getRole());
        User user1 = userService.save(user);
        uFlag = user1.getUserId() > 0;
        // 判断用户类型是否为志愿者
        if (vu.getRole().equals(2)) {
            Volunteer volunteer = new Volunteer();
            volunteer.setRole(vu.getRole());
            volunteer.setIdCard(vu.getIdCard());
            volunteer.setResidentLocation(vu.getResidentLocation());
            volunteer.setAddress(vu.getAddress());
            volunteer.setZipCode(vu.getZipCode());
            volunteer.setProfession(vu.getProfession());
            volunteer.setVtUserId(user1.getUserId());
            Volunteer volunteer1 = volunteerService.save(volunteer);
            vFlag = volunteer1.getVtUserId() > 0;
        }
        if (uFlag && vFlag) {
            return Result.success(ResultCode.USER_REGISTER_SUCCESS.getMessage());
        }
        return Result.fail(ResultCode.PARAM_TYPE_ERROR.getMessage());
    }
}