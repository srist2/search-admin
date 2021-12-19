package com.gxstnu.search.service;

import com.gxstnu.search.entity.missPerson.ContactPerson;

public interface ContactPersonService {

    /**
     * 添加/更新
     * @param contactPerson
     * @return
     */
    public ContactPerson save(ContactPerson contactPerson);

    /**
     * 删除失踪者联系人信息
     * @param cntId  失踪者联系人id
     * @return {Integer} 0 1
     */
    public int deleteByCntId(Integer cntId);
}
