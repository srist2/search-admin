package com.gxstnu.search.controller;

import com.gxstnu.search.entity.Vo.InfoAndContactVo;
import com.gxstnu.search.entity.missPerson.ContactPerson;
import com.gxstnu.search.entity.missPerson.Information;
import com.gxstnu.search.repository.InformationRepository;
import com.gxstnu.search.service.ContactPersonService;
import com.gxstnu.search.service.InformationService;
import com.gxstnu.search.utils.Result;
import com.gxstnu.search.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;
    @Resource
    private InformationRepository informationRepository;
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
        // 联系人信息
        ContactPerson contactPerson = initContactPerson(model);
        ContactPerson contactP = contactPersonService.save(contactPerson);
        // 失踪者信息
        Information information = initInformation(model);
        Information info = informationService.save(information);
        int flag = informationService.updateCtById(contactP.getContactId(), info.getInfoId());

        return Result.success(flag);
    }

    // 更新
    @PostMapping("/update")
    public Result update(@RequestBody InfoAndContactVo model) {
        // 失踪者信息
        Information information = initInformation(model);
        // 联系人信息
        ContactPerson contactPerson = initContactPerson(model);
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
        int flagCtn = contactPersonService.deleteByCntId(contactId);
        if (flagInfo == 1 && flagCtn == 1) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.fail(ResultCode.FAIL);
    }

    // 更新失踪者信息是否展示
    @PostMapping("/isShowInfo")
    public Result isShowInfo(@RequestBody Map params) {
        Integer infoId = (Integer) params.get("infoId");
        Integer isShow = (Integer) params.get("isShow");
        int flagInfo = informationService.updateIsShowById(isShow, infoId);
        if (flagInfo == 1) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.fail(ResultCode.FAIL);
    }

    // 查询展示的失踪者信息
    @GetMapping("/findAllByIsShow")
    public Result findAllByIsShow() {
        // 查询失踪者
        List<Information> informationList = informationService.findAllByIsShow(1);
        if (informationList.size() > 0) {
            return Result.success(informationList);
        } else {
            return Result.fail(500);
        }
    }

    // 根据寻找类型和是否展示倒序查询
    @PostMapping("/findSeekTypeByIsShow")
    public Result findSeekTypeByIsShow(@RequestBody Map params) {
        Integer seekType = (Integer) params.get("seekType");
        Integer isShow = (Integer) params.get("isShow");
        // 查询失踪者
        List<Information> informationList;
        if (seekType == 0) {
            informationList = informationService.findSeekOtherTypeByIsShow();
        } else {
            informationList = informationService.findSeekTypeByIsShow(seekType, isShow);
        }
        if (informationList.size() > 0) {
            return Result.success(informationList);
        } else {
            return Result.fail(500);
        }
    }

    // 根据ID查询用户信息
    @PostMapping("/findById")
    public Result findById(@RequestBody Information info) {
//        Integer infoId = Integer.parseInt(params.get("infoId").toString());
        Information information = informationService.findAllByInfoId(info.getInfoId());
        if (information != null) {
            return Result.success(information);
        }
        return Result.fail(ResultCode.PARAM_IS_INVALID.getMessage());
    }

    /**
     * 初始化Information
     *
     * @param model InfoAndContactVo
     * @return {Object} initInformation
     */
    public Information initInformation(InfoAndContactVo model) {
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
        return information;
    }

    /**
     * 初始化ContactPerson
     *
     * @param model InfoAndContactVo
     * @return {Object} ContactPerson
     */
    public ContactPerson initContactPerson(InfoAndContactVo model) {
        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setContactId(model.getContactPerson().getContactId());
        contactPerson.setCtName(model.getContactPerson().getCtName());
        contactPerson.setCtPhone(model.getContactPerson().getCtPhone());
        contactPerson.setCtEmail(model.getContactPerson().getCtEmail());
        contactPerson.setCtRemark(model.getContactPerson().getCtRemark());
        contactPerson.setCtAddress(model.getContactPerson().getCtAddress());
        contactPerson.setCtZipCode(model.getContactPerson().getCtZipCode());
        return contactPerson;
    }

    @PostMapping("/findAllByInfoName")
    public Result findAllByInfoName(@RequestBody Map params) {
        String infoName = (String) params.get("infoName");
        Integer isShow = (Integer) params.get("infoIsShow");
        List<Information> list = informationService.findAllByInfoName(infoName, isShow);
        return Result.success(list);
    }
}
