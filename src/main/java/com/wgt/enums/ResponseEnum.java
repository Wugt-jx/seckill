package com.wgt.enums;

/**
 * Created by Administrator on 2017/4/27.
 */
public enum ResponseEnum {
    SUCCESS("0000","success"),
    NONE_RESULT("0001","not found result"),
    LACK_PARAMS("0004","params format error"),
    NOT_LOGIN("0005","user not login"),
    PEREAT_KILL("0002","can't reprat kill "),
    DATA_REWRTITE("0003","reuqest data be tamper"),
    KILL_CLOSE("0006","Promotions be close")
    ;

    private String code;
    private String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResponseEnum ResponseOf(String code){
        for (ResponseEnum responseEnum:values()){
            if (responseEnum.getCode().equals(code)){
                return responseEnum;
            }
        }
        return null;
    }
}
