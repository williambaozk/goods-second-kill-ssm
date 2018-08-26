package com.william.dto;

import com.william.entity.Successkill;
import com.william.enums.GoodStateEnum;

/**
 * Created by Baozhikuan on 2018/8/19.
 */
public class GoodExecution {
    private long goodId;

    private int state;

    private String stateInfo;

    private Successkill successkill;

    public GoodExecution(long goodId, GoodStateEnum stateEnum, Successkill successkill) {
        this.goodId = goodId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successkill = successkill;
    }

    public GoodExecution(long goodId, GoodStateEnum stateEnum) {
        this.goodId = goodId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Successkill getSuccesskill() {
        return successkill;
    }

    public void setSuccesskill(Successkill successkill) {
        this.successkill = successkill;
    }

    @Override
    public String toString() {
        return "GoodExecution{" +
                "goodId=" + goodId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successkill=" + successkill +
                '}';
    }
}
