package com.gxstnu.search.utils;

import lombok.Data;

/**
 * 统一的返回类型
 * {
 *  返回状态码
 *  code: integer,
 *  返回信息描述
 *  message: string,
 *  返回值
 *  data: object
 * }
 */

@Data
public class Result<T> {
    /**
     * 返回码
     */
    private Integer status;

    /**
     * 返回消息
     */
    private String message;

    private  T data;
    private Result(){
        this.status = 200;
        this.message = "ok";
        this.data = null;
    }
    private Result(ResultCode resultCode,T data){
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 暴露外面静态方法. 成功
     */
    public static <E> Result success(E data){
        return new Result<E>(ResultCode.SUCCESS,data);
    }

    /**
     * 失败
     */
    public static <E> Result fail(E data){
        return new Result<E>(ResultCode.FAIL,data);
    }
}
