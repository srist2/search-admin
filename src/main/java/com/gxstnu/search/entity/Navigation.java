package com.gxstnu.search.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * 侧边栏
 */
@Data
@Entity
@DynamicUpdate
public class Navigation {
    @Id
    private Integer id;
    private String name;
    private Integer pid;
    private String pName;
    private String icon;
    private String url;
    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @Column(name = "update_time" )
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Integer delFlag;
    @Transient
    List<Navigation> childrens;
}