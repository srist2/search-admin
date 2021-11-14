package com.gxstnu.search.service;

import com.gxstnu.search.entity.User;
import com.gxstnu.search.utils.Result;

import java.util.Date;
import java.util.List;

public interface UserService {
    // 查询所用
    public List<User> findAll();

    // 使用SQL语句更新
    public int updateUser(String userName, String nickName, String phone, String email, String remark, Integer userId);

    // 使用User类更新
    public int saveUserByClass(User user);

    // 删除用户信息
    public void deleteByUserId(Integer id);

    // 添加用户信息
    public User save(User user);
}
