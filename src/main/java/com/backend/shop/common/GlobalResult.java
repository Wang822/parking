package com.backend.shop.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *   自定义响应数据结构
 *              200：表示成功
 *              500：表示错误，错误信息在msg字段中
 *              501：bean验证错误，不管多少个错误都以map形式返回
 *              502：拦截器拦截到用户token出错
 *              555：异常抛出信息
 */
@ApiModel(value = "Global Result", description = "Response封装")
public class GlobalResult {

    @ApiModelProperty(value = "响应业务状态",example = "200")
    private Integer status;

    @ApiModelProperty(value = "响应消息",example = "success")
    private String msg;

    @ApiModelProperty(value = "响应数据(对象)", example = "null")
    private Object data;

    @ApiModelProperty(value = "ok,未使用", example = "true")
    private String ok;

    public static GlobalResult build(Integer status, String msg, Object data) {
        return new GlobalResult(status, msg, data);
    }

    public static GlobalResult ok(Object data) {
        return new GlobalResult(data);
    }

    public static GlobalResult ok() {
        return new GlobalResult(null);
    }

    public static GlobalResult errorMsg(String msg) {
        return new GlobalResult(500, msg, null);
    }

    public static GlobalResult errorMap(Object data) {
        return new GlobalResult(501, "error", data);
    }

    public static GlobalResult errorTokenMsg(String msg) {
        return new GlobalResult(502, msg, null);
    }

    public static GlobalResult errorException(String msg) {
        return new GlobalResult(555, msg, null);
    }

    public GlobalResult() {

    }

    public GlobalResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public GlobalResult(Object data) {
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

    public void setData(Object data) {
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
