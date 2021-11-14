package com.gxstnu.search.service;

import com.gxstnu.search.entity.missPerson.ContactPerson;

public interface ContactPersonService {

    // 添加/更新
    public ContactPerson save(ContactPerson contactPerson);
    // 删除失踪者信息
    public int deleteByCntId(Integer cntId);
}
