package com.gxstnu.search.controller;

import com.gxstnu.search.entity.ToEmail;
import com.gxstnu.search.entity.User;
import com.gxstnu.search.entity.Vo.InfoAndContactVo;
import com.gxstnu.search.entity.missPerson.Claim;
import com.gxstnu.search.entity.missPerson.ContactPerson;
import com.gxstnu.search.entity.missPerson.Information;
import com.gxstnu.search.repository.ClaimRepository;
import com.gxstnu.search.service.ClaimService;
import com.gxstnu.search.service.InformationService;
import com.gxstnu.search.service.UserService;
import com.gxstnu.search.utils.Result;
import com.gxstnu.search.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    // 查询所有
    @GetMapping("/findAll")
    public Result findAll() {
        List<Claim> claimList = claimService.findAll();
        // 循环添加认领者所认领的失踪者信息和失踪者联系人
        for (Claim claim : claimList) {
            // 根据认领者id查询失踪者信息
            Information informationList = informationService.findAllByInfoId(claim.getInfoId());
            // 创建一个失踪者和联系人对象
            // 初始化失踪者信息
            InfoAndContactVo infoVo = initInfoAndContactVo(informationList);
            // 初始化联系人信息
            ContactPerson contactPerson = initContactPerson(informationList);
            // 添加联系人信息
            infoVo.setContactPerson(contactPerson);
            // 添加失踪者相关信息
            claim.setInfoAndContactVo(infoVo);
        }
        return Result.success(claimList);
    }

    // 寻人认领申请
    @PostMapping("/application")
    public Result claimApplication(@RequestBody Map params) {
        Integer infoId = (Integer) params.get("infoId");
        Integer userId = (Integer) params.get("userId");
        User user =  userService.findAddClaimById(userId);
        Claim claim = new Claim();
        claim.setClaimName(user.getNickName());
        claim.setEmail(user.getEmail());
        claim.setPhone(user.getPhone());
        claim.setInfoId(infoId);
        claim.setIsPass(0);
        claimService.save(claim);
        return Result.success(ResultCode.CLAIM_SUCCESS.getMessage());
    }

    // 寻人认领审核
    @PostMapping("/updateIsPass")
    public Result updateIsPass(@RequestBody Map params) {
        Integer claimId = (Integer) params.get("claimId");
        Integer isPass = (Integer) params.get("isPass");
        Integer infoId = (Integer) params.get("infoId");
        String email = (String) params.get("email");
        // 修改表中isPass，审核通过
        Integer flag = claimService.updateIsPass(isPass, claimId);
        ToEmail toEmail = new ToEmail();
        int sendFlag = 0;
        if (flag == 1 && isPass == 1) {
            // 审核通过后发送联系人信息到认领人邮箱中
            Information info = informationService.findAllByInfoId(infoId);
            toEmail = initEmail(new String[]{email} , info.getInfoName(), info.getContactPerson().getCtName(),
                    info.getContactPerson().getCtPhone(), info.getContactPerson().getCtEmail(),
                    info.getContactPerson().getCtAddress(), isPass);
            sendFlag = sendEmail(toEmail);

        } else {
            toEmail = initEmail(new String[]{email} , "", "","", "", "",isPass);
            sendFlag = sendEmail(toEmail);
        }
        if (sendFlag == 1) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.fail(ResultCode.FAIL);
    }

    /**
     * 邮件发送
     * @param toEmail 邮件模型
     * @return {Integer} 1:成功 2:失败
     */
    public Integer sendEmail(ToEmail toEmail) {
        // 创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        // 发送人
        message.setFrom(from);
        // 接收人
        message.setTo(toEmail.getTos());
        // 邮件标题
        message.setSubject(toEmail.getSubject());
        // 邮件内容
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
            return 1;
        } catch (MailException e) {
            return 2;
        }
    }

    /**
     * 初始化邮箱格式
     *
     * @param fromEmail 发送邮箱
     * @param infoName  失踪者姓名
     * @param CtPhone   联系人电话
     * @param CtEmail   联系人邮箱
     * @param CtAddress 联系人联系地址
     * @param isPass 审核结果 1：通过 2：不通过
     *
     * @return  {Object} ToEmail
     */
    public ToEmail initEmail(String[] fromEmail, String infoName, String CtName, String CtPhone, String CtEmail,
                             String CtAddress, Integer isPass) {
        ToEmail toEmail = new ToEmail();
        // 邮件标题
        String subject = isPass == 1 ? "您认领的失踪者" + infoName + "，审核通过了！" : "您的审核不通过";
        String content = isPass == 1 ? "您认领的失踪者的联系人信息为：\n \t姓名：" + CtName
                + "\n \t邮箱：" + CtEmail
                + "\n \t电话号码：" + CtPhone
                + "\n \t联系地址：" + CtAddress : "可能是资料不够匹配，请您增加耐心";
        // 接收人
        toEmail.setTos(fromEmail);
        // 邮件标题
        toEmail.setSubject(subject);
        // 邮件内容
        toEmail.setContent(content);
        return toEmail;
    }

    /**
     * 初始化Information
     *
     * @param info Information
     *
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
     *
     * @param info Information
     *
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
