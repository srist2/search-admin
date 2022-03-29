package com.gxstnu.search.service.impl;

import com.gxstnu.search.entity.User;
import com.gxstnu.search.entity.Vo.DateVo;
import com.gxstnu.search.repository.UserRepository;
import com.gxstnu.search.service.UserService;
import com.gxstnu.search.utils.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询所有用户
     *
     * @return {List} User
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 使用JPA Update语句更新
     *
     * @param userName 用户名
     * @param nickName 姓名
     * @param phone    手机号
     * @param email    电子邮箱
     * @param remark   备注
     * @param userId   用户ID
     * @return {Integer} 1:成功 0:失败
     */
    @Override
    public int updateUser(String userName, String nickName, String phone, String email, String remark, Integer userId) {
        return userRepository.updateUser(userName, nickName, phone, email, remark, userId);
    }

    /**
     * 使用User类更新
     *
     * @param user
     * @return {Integer} 1:成功 0:失败
     */
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

    /**
     * 根据user_id删除用户
     *
     * @param id 用户Id
     */
    @Override
    public void deleteByUserId(Integer id) {
        userRepository.deleteByUserId(id);
    }

    /**
     * 添加用户信息
     *
     * @param user
     * @return {Object} User
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * 判断用户账户是否存在
     *
     * @param userName 账号
     * @return 1: 成功 0: 失败
     */
    @Override
    public int findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param password 用户密码
     * @return 1: 成功 2: 失败
     */
    @Override
    public User login(String userName, String password) {
        User user = new User();
        List<Map<String, User>> userList = userRepository.login(userName, password);
        // 判断用户和密码是否正确
        if (userList.size() == 0) {
            return user;
        }
        for (Map<String, User> user2 : userList) {
            user.setUserId(Integer.valueOf(String.valueOf(user2.get("userId"))));
            user.setUserName(String.valueOf(user2.get("userName")));
            user.setPassword(String.valueOf(user2.get("password")));
            user.setStatus(Integer.valueOf(String.valueOf(user2.get("status"))));
            user.setRole(Integer.valueOf(String.valueOf(user2.get("role"))));
        }
        return user;
    }

    /**
     * 根据用户ID查询 用于添加到失踪认领表 返回用户名、手机号、邮箱
     *
     * @param userId 用户ID
     * @return {nickName, phone, email} User
     */
    @Override
    public User findAddClaimById(Integer userId) {
        User user = new User();
        List<Map<String, Object>> userList = userRepository.findAddClaimById(userId);
        if (userList.size() == 0) {
            return user;
        }
        for (Map<String, Object> user2 : userList) {
            user.setNickName((String) user2.get("nickName"));
            user.setEmail((String) user2.get("email"));
            user.setPhone((String) user2.get("phone"));
        }
        return user;
    }

    /**
     * 根据用户名查询用户ID
     *
     * @param userName 用户名
     * @return
     */
    @Override
    public User findIdByUserName(String userName) {
        return userRepository.findIdByUserName(userName);
    }

    /**
     * 获取用户类型数量
     *
     * @return
     */
    @Override
    public DateVo getUserTypeNumber() {
        DateVo dateVo = new DateVo();
        List<Map<String, Object>> userList = userRepository.getUserTypeNumber();
        for (Map<String, Object> user2 : userList) {
            dateVo.setVolunteerNumber(Integer.parseInt(user2.get("volunteerNumber").toString()));
            dateVo.setAdminNumber(Integer.parseInt(user2.get("adminNumber").toString()));
            dateVo.setUserNumber(Integer.parseInt(user2.get("userNumber").toString()));
            dateVo.setGeneralUserNumber(Integer.parseInt(user2.get("generalUserNumber").toString()));
        }
        return dateVo;
    }
}
