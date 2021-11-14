package com.gxstnu.search.repository;

import com.gxstnu.search.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

    // 志愿者详细信息查询
    @Query(value = "SELECT u.user_id as userId, vol.volunteer_id as volunteerId, u.user_name as userName,u.nick_name as nickName,u.phone,u.email,u.remark,vol.id_card as idCard," +
            "vol.resident_location as residentLocation,vol.address,vol.zip_code as zipCode,vol.profession ,u.status,u.create_time as createTime,u.update_time as updateTime " +
            "FROM volunteer as vol, user as u where vol.vt_user_id = u.user_id and vol.role = u.role", nativeQuery = true)
    public List<Map<String, Object>> findVolunteerList();

    // 删除志愿者信息
    @Modifying
    @Transactional
    public void deleteByVtUserId(Integer id);

    // 查询志愿者信息
    @Modifying
    @Transactional
    @Query(value = "select vlt from volunteer as vlt where vlt.role=?1")
    public List<Volunteer> findByVtUserId(Integer id);
}