package com.backend.shop.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

/**
 *   自定义响应数据结构
 *              200：表示成功
 *              500：表示错误，错误信息在msg字段中
 *              501：bean验证错误，不管多少个错误都以map形式返回
 *              502：拦截器拦截到用户token出错
 *              555：异常抛出信息
 */
@ApiModel(value = "Global Result", description = "Response封装")
//@Builder
public class GlobalResult<T> {

    @ApiModelProperty(value = "响应业务状态")
    private Integer status;

    @ApiModelProperty(value = "响应消息")
    private String msg;

    @ApiModelProperty(value = "响应数据(对象)", dataType = "Object")
    private T data;

    @ApiModelProperty(value = "ok,未使用")
    private String ok;

    public static <T> GlobalResult<T> build(Integer status, String msg, T data) {
        return new GlobalResult<T>(status, msg, data);
    }

    public static <T> GlobalResult<T> ok(T data) {
        return new GlobalResult<T>(data);
    }

    public static <T> GlobalResult<T> ok() {
        return new GlobalResult<T>(null);
    }

    public static <T> GlobalResult<T> errorMsg(String msg) {
        return new GlobalResult<T>(500, msg, null);
    }

    public static <T> GlobalResult<T> errorMap(T data) {
        return new GlobalResult<T>(501, "error", data);
    }

    public static <T> GlobalResult<T> errorTokenMsg(String msg) {
        return new GlobalResult<T>(502, msg, null);
    }

    public static <T> GlobalResult<T> errorException(String msg) {
        return new GlobalResult<T>(555, msg, null);
    }

    public GlobalResult() {

    }

    public GlobalResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public GlobalResult(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public GlobalResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

//    public String getOk() {
//        return ok;
//    }
//
//    public void setOk(String ok) {
//        this.ok = ok;
//    }

}
