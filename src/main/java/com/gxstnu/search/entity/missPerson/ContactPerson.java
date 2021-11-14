package com.gxstnu.search.entity.missPerson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 失踪者联系人
 */

//@Data
@Entity(name = "miss_person_contact")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class ContactPerson {

    // 失踪者联系人id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ct_id")
    private Integer contactId;
    @Column(name = "ct_name")
    private String ctName;
    @Column(name = "ct_phone")
    private String ctPhone;
    @Column(name = "ct_email")
    private String ctEmail;
    @Column(name = "ct_remark")
    private String ctRemark;
    @Column(name = "ct_address")
    private String ctAddress;
    @Column(name = "ct_zipCode")
    private String ctZipCode;
    //一个失踪者联系人有多个寻人信息
    //fetch=FetchType.EAGER:立即加载
    @OneToMany(mappedBy = "contactPerson",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Information> informationSet = new HashSet<Information>();

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getCtPhone() {
        return ctPhone;
    }

    public void setCtPhone(String ctPhone) {
        this.ctPhone = ctPhone;
    }

    public String getCtEmail() {
        return ctEmail;
    }

    public void setCtEmail(String ctEmail) {
        this.ctEmail = ctEmail;
    }

    public String getCtRemark() {
        return ctRemark;
    }

    public void setCtRemark(String ctRemark) {
        this.ctRemark = ctRemark;
    }

    public String getCtAddress() {
        return ctAddress;
    }

    public void setCtAddress(String ctAddress) {
        this.ctAddress = ctAddress;
    }

    public String getCtZipCode() {
        return ctZipCode;
    }

    public void setCtZipCode(String ctZipCode) {
        this.ctZipCode = ctZipCode;
    }

    public Set<Information> getInformationSet() {
        return informationSet;
    }

    public void setInformationSet(Set<Information> informationSet) {
        this.informationSet = informationSet;
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "contactId=" + contactId +
                ", ctName='" + ctName + '\'' +
                ", ctPhone='" + ctPhone + '\'' +
                ", ctEmail='" + ctEmail + '\'' +
                ", ctRemark='" + ctRemark + '\'' +
                ", ctAddress='" + ctAddress + '\'' +
                ", ctZipCode='" + ctZipCode + '\'' +
                '}';
    }
}
