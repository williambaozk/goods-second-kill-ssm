package com.william.enums;

/**
 * Created by Baozhikuan on 2018/8/20.
 */
public enum GoodStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");

    private int state;

    private String stateInfo;

    GoodStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static GoodStateEnum stateOf(int index){
        for(GoodStateEnum state : values()){
            if(state.getState()==index){
                return state;
            }
        }
        return null;
    }
}
