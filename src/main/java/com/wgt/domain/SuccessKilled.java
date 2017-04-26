package com.wgt.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/26.
 */
public class SuccessKilled implements Serializable {
    private static final long serialVersionUID = -6709068864146526719L;

    private Long seckillId;
    private Long userPhone;
    private short state;
    private Date createTime;

    //多对一
    private Seckill seckill;

    public SuccessKilled() {
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckill=" + seckill +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
