package com.gxstnu.search.repository;

import com.gxstnu.search.entity.missPerson.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Integer> {

    @Modifying
    @Transactional
    @Query(value = "select ct from miss_person_contact as ct where ct.contactId=?1")
    public List<ContactPerson> findByCtId(Integer id);

    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "delete miss_person_contact where contactId = ?1")
    public int deleteByCntId(Integer id);
}
