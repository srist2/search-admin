package com.gxstnu.search.utils;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 * enum 枚举类
 */

@Getter
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200, "success"),
    FAIL(500, "fail"),
    /**
     * 错误参数
     */
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    /**
     * 用户成功
     */
    USER_LOGIN_SUCCESS(2010, "登录成功"),
    USER_REGISTER_SUCCESS(2011, "注册成功"),
    USER_RESET_PASSWORD(2012, "重置密码成功"),
    /**
     * 用户错误
     */
    USER_NOT_LOGIN_IN(2001, "用户未登录"),
    USER_LOGIN_ERROR(2002, "账号或者密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账户被禁用"),
    USER_NOT_EXISTS(2004, "用户不存在"),
    USER_HAS_EXISTED(2005, "用户已存在，请更改用户名"),
    USER_VERIFY_EMAIL(2006, "邮箱验证失败，请确认与注册时邮箱一致"),
    /**
     * 邮件错误
     */
    EMAIL_FEND_SUCCESS(3001, "邮件发送成功"),
    EMAIL_FEND_ERROR(3002, "邮件发送失败"),
    /**
     * 认领
     */
    CLAIM_SUCCESS(4001, "认领申请提交成功，审核通过后通过邮箱通知"),
    CLAIM_FAIL(4002, "认领申请提交失败");

    /**
     * 错误代码
     */
    private Integer status;
    /**
     * 提示信息
     */
    private String message;

    private ResultCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
