package com.gxstnu.search.controller;

import com.gxstnu.search.entity.Vo.InfoAndContactVo;
import com.gxstnu.search.entity.missPerson.Claim;
import com.gxstnu.search.entity.missPerson.ContactPerson;
import com.gxstnu.search.entity.missPerson.Information;
import com.gxstnu.search.service.ClaimService;
import com.gxstnu.search.service.InformationService;
import com.gxstnu.search.utils.Result;
import com.gxstnu.search.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/claim")
public class ClaimController {

    @Autowired
    private ClaimService claimService;
    @Autowired
    private InformationService informationService;

    // 查询所有
    @GetMapping("/findAll")
    public Result findAll() {
        List<Claim> claimList = claimService.findAll();
        for(Claim claim : claimList) {
            List<InfoAndContactVo> infoVoList = new ArrayList<>();
            InfoAndContactVo infoAndContactVo = new InfoAndContactVo();
            List<Information> informationList = informationService.findAllByInfoId(claim.getInfoId());
            for (Information info : informationList) {
                InfoAndContactVo infoVo = initInfoAndContactVo(info);
                ContactPerson contactPerson = initContactPerson(info);
                infoVo.setContactPerson(contactPerson);
                infoVoList.add(infoVo);
                infoAndContactVo = infoVo;
            }
            claim.setInfoAndContactVo(infoAndContactVo);
        }
        return Result.success(claimList);
    }

    // 寻人认领审核
    @PostMapping("/updateIsPass")
    public Result updateIsPass(@RequestBody Map params) {
        Integer claimId = (Integer) params.get("claimId");
        Integer isPass = (Integer) params.get("isPass");
        System.out.println("claimId"+ claimId + isPass);
        Integer flag = claimService.updateIsPass(isPass, claimId);
        if (flag == 1) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.fail(ResultCode.FAIL);
    }

    /**
     * 初始化Information
     * @param info Information
     * @return {Object} initInformation
     */
    public InfoAndContactVo initInfoAndContactVo(Information info) {
        InfoAndContactVo infoVo = new InfoAndContactVo();
        infoVo.setInfoId(info.getInfoId());
        infoVo.setInfoName(info.getInfoName());
        infoVo.setInfoAvatar(info.getInfoAvatar());
        infoVo.setInfoSex(info.getInfoSex());
        infoVo.setInfoHometown(info.getInfoHometown());
        infoVo.setInfoSeekType(info.getInfoSeekType());
        infoVo.setInfoMissType(info.getInfoMissType());
        infoVo.setInfoDateBirth(info.getInfoDateBirth());
        infoVo.setInfoDateMiss(info.getInfoDateMiss());
        infoVo.setInfoMissHigh(info.getInfoMissHigh());
        infoVo.setInfoMissPlace(info.getInfoMissPlace());
        infoVo.setInfoDescribe(info.getInfoDescribe());
        return infoVo;
    }

    /**
     * 初始化ContactPerson
     * @param info Information
     * @return {Object} ContactPerson
     */
    public ContactPerson initContactPerson(Information info) {
        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setContactId(info.getContactPerson().getContactId());
        contactPerson.setCtName(info.getContactPerson().getCtName());
        contactPerson.setCtPhone(info.getContactPerson().getCtPhone());
        contactPerson.setCtEmail(info.getContactPerson().getCtEmail());
        contactPerson.setCtRemark(info.getContactPerson().getCtRemark());
        contactPerson.setCtAddress(info.getContactPerson().getCtAddress());
        contactPerson.setCtZipCode(info.getContactPerson().getCtZipCode());
        return contactPerson;
    }
}
