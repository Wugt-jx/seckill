package com.wgt.dto;

import com.wgt.enums.ResponseEnum;

/**
 * Created by Administrator on 2017/4/27.
 */
public class Response<T> {
    private String code;
    private String msg;
    private T data;


    public Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(ResponseEnum responseEnum,T data) {
        this.code =responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.data =data;
    }

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    public Response() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
