package com.wgt.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/26.
 */
public class Seckill implements Serializable{

    private static final long serialVersionUID = -3729182606632458944L;

    private Long seckillId;
    private String name;
    private Integer number;
    private Date startTime;
    private Date endTime;
    private Date cteateTime;

    public Seckill() {
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCteateTime() {
        return cteateTime;
    }

    public void setCteateTime(Date cteateTime) {
        this.cteateTime = cteateTime;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", cteateTime=" + cteateTime +
                '}';
    }
}
