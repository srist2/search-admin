package com.gxstnu.search.repository;

import com.gxstnu.search.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 更新用户表信息
    @Modifying
    @Transactional
    @Query(value = "update user as u set u.userName = ?1, u.nickName = ?2, u.phone = ?3, u.email = ?4, u.remark = ?5 where u.userId = ?6")
    public int updateUser(String userName, String nickName, String phone, String email, String remark, Integer userId);

    // 删除用户信息
    @Modifying
    @Transactional
    public void deleteByUserId(Integer id);

    // 查询用户账号是否存在
    @Query(value = "select count (u.userName) from user as u where u.userName=?1")
    public int findByUserName(String userName);

    // 判断用户账号登录信息
    @Query(value = "select u.userId as userId, u.userName as userName, u.password as password, u.status as status, u.role as role from user as u where u.userName=?1 and u.password=?2")
    public List<Map<String,User>> login(String userName, String password);

    // 根据用户ID查询 用于添加到失踪认领表 返回用户名、手机号、邮箱
    @Query(value = "select nick_name as nickName, phone, email from user where user_id = ?1", nativeQuery = true)
    public List<Map<String, Object>> findAddClaimById(Integer userId);

    // 根据用户名查询用户ID
    @Query(value = "select u from user as u where u.userName=?1")
    public User findIdByUserName(String userName);

    // 获取用户类型数量
    @Query(value = "select sum(role = 1) as generalUserNumber, sum(role = 2) as volunteerNumber, sum(role = 3) as adminNumber, count(*) as userNumber from user", nativeQuery = true)
    public List<Map<String,Object>> getUserTypeNumber();

}
