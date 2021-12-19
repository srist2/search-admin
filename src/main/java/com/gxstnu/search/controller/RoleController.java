package com.gxstnu.search.controller;

import com.gxstnu.search.entity.Role;
import com.gxstnu.search.repository.RoleRepository;
import com.gxstnu.search.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    /**
     * 查询所有
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<Role> roleList = roleRepository.findAll();
        return Result.success(roleList);
    }
}
