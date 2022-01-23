package com.gxstnu.search.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户表
 */
@Data
@Entity(name = "user")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String userName;
    private String password;
    private String nickName;
    private String phone;
    private String email;
    private String remark;
    private Integer status;
    private Integer role;

    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Column(name = "update_time" )
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

//    //在主表对应的持久化类描述类与类的关系
//    @OneToMany(targetEntity = Volunteer.class, mappedBy = "users", cascade = {CascadeType.ALL})
//    private Set<Volunteer> VolunteerSet = new HashSet<>(0);

    @Transient
    private Volunteer volunteer;
    // token
    @Transient
    private String token;
}
