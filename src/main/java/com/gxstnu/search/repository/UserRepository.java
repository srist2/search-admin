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
    @Query(value = "select u.userName as userName, u.password as password, u.status as status from user as u where u.userName=?1 and u.password=?2")
    public List<Map<String,User>> login(String userName, String password);
}
