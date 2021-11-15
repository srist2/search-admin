package com.gxstnu.search.controller;

import com.gxstnu.search.entity.Vo.InfoAndContactVo;
import com.gxstnu.search.entity.missPerson.ContactPerson;
import com.gxstnu.search.entity.missPerson.Information;
import com.gxstnu.search.service.ContactPersonService;
import com.gxstnu.search.service.InformationService;
import com.gxstnu.search.utils.Result;
import com.gxstnu.search.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;
    @Autowired
    private ContactPersonService contactPersonService;

    // 查询所有
    @GetMapping("/findAll")
    public Result findAll() {
        // 查询失踪者
        List<Information> informationList = informationService.findAll();
        if (informationList.size() > 0) {
            return Result.success(informationList);
        } else {
            return Result.fail(500);
        }
    }

    // 添加失踪者信息
    @PostMapping("/add")
    public Result add(@RequestBody InfoAndContactVo model) {
        System.out.println("model" + model);
        // 联系人信息
        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setContactId(model.getContactPerson().getContactId());
        contactPerson.setCtName(model.getContactPerson().getCtName());
        contactPerson.setCtPhone(model.getContactPerson().getCtPhone());
        contactPerson.setCtEmail(model.getContactPerson().getCtEmail());
        contactPerson.setCtRemark(model.getContactPerson().getCtRemark());
        contactPerson.setCtAddress(model.getContactPerson().getCtAddress());
        contactPerson.setCtZipCode(model.getContactPerson().getCtZipCode());
        ContactPerson contactP = contactPersonService.save(contactPerson);
        System.out.println("contactP" + contactP);
        // 失踪者信息
        Information information = new Information();
        information.setInfoId(model.getInfoId());
        information.setInfoName(model.getInfoName());
        information.setInfoAvatar(model.getInfoAvatar());
        information.setInfoSex(model.getInfoSex());
        information.setInfoHometown(model.getInfoHometown());
        information.setInfoSeekType(model.getInfoSeekType());
        information.setInfoMissType(model.getInfoMissType());
        information.setInfoDateBirth(model.getInfoDateBirth());
        information.setInfoDateMiss(model.getInfoDateMiss());
        information.setInfoMissHigh(model.getInfoMissHigh());
        information.setInfoMissPlace(model.getInfoMissPlace());
        information.setInfoDescribe(model.getInfoDescribe());
        Information info = informationService.save(information);
        System.out.println("info" + info);
        int flag = informationService.updateCtById(contactP.getContactId(), info.getInfoId());

        return Result.success(flag);

    }

    // 更新
    @PostMapping("/update")
    public Result update(@RequestBody InfoAndContactVo model) {
        // 失踪者信息
        Information information = new Information();
        information.setInfoId(model.getInfoId());
        information.setInfoName(model.getInfoName());
        information.setInfoAvatar(model.getInfoAvatar());
        information.setInfoSex(model.getInfoSex());
        information.setInfoHometown(model.getInfoHometown());
        information.setInfoSeekType(model.getInfoSeekType());
        information.setInfoMissType(model.getInfoMissType());
        information.setInfoDateBirth(model.getInfoDateBirth());
        information.setInfoDateMiss(model.getInfoDateMiss());
        information.setInfoMissHigh(model.getInfoMissHigh());
        information.setInfoMissPlace(model.getInfoMissPlace());
        information.setInfoDescribe(model.getInfoDescribe());
        // 联系人信息
        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setContactId(model.getContactPerson().getContactId());
        contactPerson.setCtName(model.getContactPerson().getCtName());
        contactPerson.setCtPhone(model.getContactPerson().getCtPhone());
        contactPerson.setCtEmail(model.getContactPerson().getCtEmail());
        contactPerson.setCtRemark(model.getContactPerson().getCtRemark());
        contactPerson.setCtAddress(model.getContactPerson().getCtAddress());
        contactPerson.setCtZipCode(model.getContactPerson().getCtZipCode());
        // 更新
        int flag = informationService.saveByClass(information);
        contactPersonService.save(contactPerson);
        return Result.success(flag);
    }


    // 根据Id删除用户
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody Map params) {
        Integer infoId = (Integer) params.get("infoId");
        Integer contactId = (Integer) params.get("contactId");
        int flagInfo = informationService.deleteByInfoId(infoId);
        int flagCtn = informationService.deleteByInfoId(infoId);
        if (flagInfo == 1 && flagCtn == 1) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.fail(ResultCode.FAIL);
    }

    @PostMapping("/isShowInfo")
    public Result isShowInfo(@RequestBody Map params) {
        Integer infoId = (Integer) params.get("infoId");
        Integer isShow = (Integer) params.get("isShow");
        int flagInfo = informationService.updateIsShowById(isShow, infoId);
        System.out.println("flagInfo" + flagInfo);
        if (flagInfo == 1) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.fail(ResultCode.FAIL);
    }
}
