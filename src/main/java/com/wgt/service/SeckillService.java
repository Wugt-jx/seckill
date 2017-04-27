package com.wgt.service;

import com.wgt.domain.Seckill;
import com.wgt.dto.Exposer;
import com.wgt.dto.SeckillExecution;
import com.wgt.exception.RepeatKillException;
import com.wgt.exception.SeckillCloseException;
import com.wgt.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在“ 使用者”角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（returne 类型/异常）
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口的地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;
}
