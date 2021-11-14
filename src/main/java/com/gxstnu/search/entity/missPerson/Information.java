package com.gxstnu.search.entity.missPerson;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 寻人信息表
 */
@Data
@ToString(exclude={"contactPerson"})
@Entity(name = "miss_person_information")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Information {
    // 寻人信息id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Integer infoId;
    @Column(name = "info_name")
    private String infoName;
    @Column(name = "info_avatar")
    private String infoAvatar;
    @Column(name = "info_sex")
    private Integer infoSex;
    @Column(name = "info_hometown")
    private String infoHometown;
    @Column(name = "info_seek_type")
    private Integer infoSeekType;
    @Column(name = "info_miss_type")
    private Integer infoMissType;
    @Column(name = "info_date_birth")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date infoDateBirth;
    @Column(name = "info_date_miss")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date infoDateMiss;
    @Column(name = "info_miss_high")
    private String infoMissHigh;
    @Column(name = "info_miss_place")
    private String infoMissPlace;
    @Column(name = "info_describe")
    private String infoDescribe;
    @Column(name = "info_is_show")
    private Integer infoIsShow;
    //多个寻人信息对应一个联系人（多对一）
    @ManyToOne
    @JoinColumn(name = "ct_by_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "contactId")
    private ContactPerson contactPerson;
}
