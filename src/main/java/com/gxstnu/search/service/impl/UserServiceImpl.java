package com.gxstnu.search.service.impl;

import com.gxstnu.search.entity.User;
import com.gxstnu.search.entity.Volunteer;
import com.gxstnu.search.repository.UserRepository;
import com.gxstnu.search.service.UserService;
import com.gxstnu.search.utils.JpaUtil;
import com.gxstnu.search.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(cacheNames = "user", unless = "#result == null")
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // 使用JPA Update语句更新
    @Override
    public int updateUser(String userName, String nickName, String phone, String email, String remark, Integer userId) {
        return userRepository.updateUser(userName, nickName, phone, email, remark, userId);
    }

    // 使用User类更新
    @Override
    public int saveUserByClass(User user) {
        int flag = 0;
        if (user != null && user.getUserId() != null) {
            User user2 = userRepository.getOne(user.getUserId());
            if (user2 != null) {
                //将apply中非空的值赋给apply1中,并将apply1重新存储
                JpaUtil.copyNotNullProperties(user, user2);
            }
            flag = userRepository.save(user2).getUserId();
        }
        return flag;
    }

    // 删除用户信息
    @Override
    public void deleteByUserId(Integer id) {
        userRepository.deleteByUserId(id);
    }

    // 添加用户信息
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
