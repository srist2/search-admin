package com.gxstnu.search.entity.missPerson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gxstnu.search.entity.Navigation;
import com.gxstnu.search.entity.Vo.InfoAndContactVo;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 寻人寻亲认领表
 */
@Data
@DynamicUpdate
@Entity(name = "miss_person_claim")
@EntityListeners(AuditingEntityListener.class)
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cl_id")
    // 认领人Id
    private Integer claimId;
    // 认领人姓名
    private String claimName;
    // 手机号
    private String phone;
    // 电子邮箱
    private String email;
    // 失踪者Id
    private Integer infoId;
    // 是否通过 1:通过 2:不通过
    private Integer isPass;
    // 创建时间
    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    // 修改时间
    @Column(name = "update_time" )
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    @Transient
    InfoAndContactVo infoAndContactVo;
}
