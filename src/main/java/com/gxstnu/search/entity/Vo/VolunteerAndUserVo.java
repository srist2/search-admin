package com.gxstnu.search.entity.Vo;

import com.gxstnu.search.entity.User;
import com.gxstnu.search.entity.Volunteer;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class VolunteerAndUserVo {
    private Integer userId;
    private Integer volunteerId;
    private String userName;
    private String nickName;
    private String password;
    private String phone;
    private String email;
    private String remark;
    private String idCard;
    private String residentLocation;
    private String address;
    private String zipCode;
    private String profession;
    private Integer status;
    private Integer role;
    private String createTime;
    private String updateTime;
}
