package com.gxstnu.search.service;

import com.gxstnu.search.entity.User;
import com.gxstnu.search.entity.Vo.DateVo;
import com.gxstnu.search.utils.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查询所有用户
     * @return {List} User
     */
    public List<User> findAll();

    /**
     * 使用JPA Update语句更新
     *
     * @param userName  用户名
     * @param nickName  姓名
     * @param phone     手机号
     * @param email     电子邮箱
     * @param remark    备注
     * @param userId    用户ID
     *
     * @return {Integer} 1:成功 0:失败
     */
    public int updateUser(String userName, String nickName, String phone, String email, String remark, Integer userId);

    /**
     * 使用User类更新
     *
     * @param user
     *
     * @return {Integer} 1:成功 0:失败
     */
    public int saveUserByClass(User user);

    /**
     * 根据user_id删除用户
     *
     * @param id 用户Id
     */
    public void deleteByUserId(Integer id);

    /**
     * 添加用户信息
     * @param user
     * @return {Object} User
     */
    public User save(User user);

    /**
     * 判断用户账户是否存在
     * @param userName  账号
     * @return 1: 成功 0: 失败
     */
    public int findByUserName(String userName);

    /**
     * 用户登录
     * @param userName  用户名
     * @param password  用户密码
     * @return  1: 成功 2: 失败
     */
    public User login(String userName, String password);

    /**
     * 根据用户ID查询 用于添加到失踪认领表 返回用户名、手机号、邮箱
     * @param userId
     * @return {nickName, iphone, email} User
     */
    public User findAddClaimById(Integer userId);

    /**
     * 根据账号查询用户ID
     * * @param userName 账号
     * @return
     */
    public User findIdByUserName(String userName);

    /**
     *
     * @return
     */
    public DateVo getUserTypeNumber();
}
