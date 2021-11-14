package com.gxstnu.search.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户权限
 */
@Data
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status")
    private Integer status;
    @Column(name = "type")
    private String type;
    @Column(name = "disable")
    private Boolean disable;
}
