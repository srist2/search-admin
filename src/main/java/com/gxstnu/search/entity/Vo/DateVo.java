package com.gxstnu.search.entity.Vo;

import lombok.Data;

import java.sql.Array;
import java.util.List;

/**
 * 首页数据
 */
@Data
public class DateVo {
    // 志愿者数量
    private Integer volunteerNumber;
    // 普通用户数量
    private Integer generalUserNumber;
    // 管理员数量
    private Integer adminNumber;
    // 总用户数量
    private Integer userNumber;
    // 联系人数量
    private Integer claimNumber;
    // 认领人数量
    private Integer contactNumber;
    // 失踪者男性人数
    private Integer missManNumber;
    // 失踪者女性人数
    private Integer missWomanNumber;

    private List<Integer> missType;
    // 失踪者男性类型
    private List<MissType> missManTypeLists;
    // 失踪者女性类型
    private List<MissType> missWomanTypeLists;
    // 失踪者女性类型
    private List<MissType> seekTypeLists;
}
