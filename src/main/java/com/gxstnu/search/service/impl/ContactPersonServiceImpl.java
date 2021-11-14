package com.gxstnu.search.service.impl;

import com.gxstnu.search.entity.missPerson.ContactPerson;
import com.gxstnu.search.repository.ContactPersonRepository;
import com.gxstnu.search.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactPersonServiceImpl implements ContactPersonService {

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @Override
    public ContactPerson save(ContactPerson contactPerson) {
        return contactPersonRepository.save(contactPerson);
    }

    /**
     * 删除失踪者联系人信息
     * @param cntId  失踪者联系人id
     * @return {Integer} 0 1
     */
    @Override
    public int deleteByCntId(Integer cntId) {
        return contactPersonRepository.deleteByCntId(cntId);
    }
}
