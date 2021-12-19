package com.gxstnu.search.controller;

import com.gxstnu.search.entity.User;
import com.gxstnu.search.entity.Vo.VolunteerAndUserVo;
import com.gxstnu.search.entity.missPerson.ContactPerson;
import com.gxstnu.search.entity.missPerson.Information;
import com.gxstnu.search.repository.ContactPersonRepository;
import com.gxstnu.search.repository.InformationRepository;
import com.gxstnu.search.service.UserService;
import com.gxstnu.search.service.VolunteerService;
import com.gxstnu.search.utils.Result;
import com.gxstnu.search.utils.ResultCode;
import com.gxstnu.search.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VolunteerService volunteerService;
    @Autowired
    private ContactPersonRepository contactPersonRepository;

    /**
     * 查询所有
     */
    @GetMapping("/findAll")
    public List<User> findAll() {
        List<User> userList = userService.findAll();
        return userList;
    }

    /**
     * 添加用户
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        User user1 = userService.save(user);
        return Result.success(1);
    }

    /**
     * 根据Id删除用户
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody Map params) {
        Integer userId = (Integer) params.get("userId");
        userService.deleteByUserId(userId);
        return Result.success(1);
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        Utils utils = new Utils();
        // 增加时间戳
        user.setUpdateTime(utils.getNowDate());
        int flag = userService.saveUserByClass(user);
        if (flag == 0) {
            return Result.fail(flag);
        }
        return Result.success(flag);
    }

    /**
     * 更改用户状态
     */
    @PostMapping("/updateStatus")
    public Result updateUserStatus(@RequestBody User user) {
        int flag = userService.saveUserByClass(user);
        if (flag == 0) {
            return Result.fail(flag);
        }
        return Result.success(flag);
    }

    @GetMapping("/testFindAll2")
    public List<ContactPerson> testFindAll2() {
        List<ContactPerson> userList = contactPersonRepository.findByCtId(1);
        return userList;
    }
}
