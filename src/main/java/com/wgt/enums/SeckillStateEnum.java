package com.wgt.enums;

/**
 * 页面响应数据字典
 */
public enum  SeckillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    PEREAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRTITE(-3,"数据篡改"    )
    ;

    private Integer state;
    private String stateInfo;

    SeckillStateEnum(Integer state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public Integer getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf(int index){
        for (SeckillStateEnum state:values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
