package com.gxstnu.search.entity.Vo;

import com.gxstnu.search.entity.missPerson.ContactPerson;
import lombok.Data;

import java.util.Date;

@Data
public class InfoAndContactVo {
    private Integer infoId;
    private String infoName;
    private String infoAvatar;
    private Integer infoSex;
    private String infoHometown;
    private Integer infoSeekType;
    private Integer infoMissType;
    private Date infoDateBirth;
    private Date infoDateMiss;
    private String infoMissHigh;
    private String infoMissPlace;
    private String infoDescribe;
    private ContactPerson contactPerson;
}
