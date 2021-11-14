package com.gxstnu.search.repository.dict;

import com.gxstnu.search.entity.dict.MissTypeDict;
import com.gxstnu.search.entity.missPerson.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissTypeDictRepository extends JpaRepository<MissTypeDict, Integer> {
}
