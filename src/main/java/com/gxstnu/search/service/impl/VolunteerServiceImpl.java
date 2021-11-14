package com.gxstnu.search.service.impl;

import com.gxstnu.search.entity.Volunteer;
import com.gxstnu.search.repository.VolunteerRepository;
import com.gxstnu.search.service.VolunteerService;
import com.gxstnu.search.utils.JpaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Override
    public List<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> findVolunteerList() {
        return volunteerRepository.findVolunteerList();
    }

    @Override
    public int saveVolunteerByClass(Volunteer volunteer) {
        int flag = 0;
        if (volunteer != null && volunteer.getVolunteerId() != null) {
            Volunteer vlt = volunteerRepository.getOne(volunteer.getVolunteerId());
            if (vlt != null) {
                //将apply中非空的值赋给apply1中,并将apply1重新存储
                JpaUtil.copyNotNullProperties(volunteer, vlt);
            }
            flag = volunteerRepository.save(vlt).getVolunteerId();
        }
        return flag;
    }

    @Override
    public void deleteByVtUserId(Integer id) {
        volunteerRepository.deleteByVtUserId(id);
    }

    @Override
    public Volunteer save(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public List<Volunteer> findByVtUserId(Integer id) {
        return volunteerRepository.findByVtUserId(id);
    }
}
