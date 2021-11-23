package com.gxstnu.search.entity.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gxstnu.search.entity.missPerson.ContactPerson;
import lombok.Data;

import java.util.Date;

/**
 * 失踪者和联系人对象
 */
@Data
public class InfoAndContactVo {
    private Integer infoId;
    private String infoName;
    private String infoAvatar;
    private Integer infoSex;
    private String infoHometown;
    private Integer infoSeekType;
    private Integer infoMissType;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date infoDateBirth;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date infoDateMiss;
    private String infoMissHigh;
    private String infoMissPlace;
    private String infoDescribe;
    private ContactPerson contactPerson;
}
