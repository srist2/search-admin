package com.gxstnu.search.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 志愿者信息
 */
@Data
@DynamicUpdate     //动态更新
@Entity(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer volunteerId;
    private Integer role;
    private String idCard;
    private String residentLocation;
    private String address;
    private String zipCode;
    private String profession;
    private Integer vtUserId;
    //在多的一方配置表与表的关系
    //referencedColumnName 配置 被引用的字段名
    //name 配置 外键的字段名
//    @ManyToOne(targetEntity = User.class)
//    @JoinColumn(name = "vt_user_id",referencedColumnName = "user_id")
//    private User users;
}
