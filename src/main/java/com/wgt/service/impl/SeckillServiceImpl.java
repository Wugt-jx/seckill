package com.wgt.service.impl;

import com.wgt.dao.SeckillDao;
import com.wgt.dao.SuccessKilledDao;
import com.wgt.domain.Seckill;
import com.wgt.domain.SuccessKilled;
import com.wgt.dto.Exposer;
import com.wgt.dto.SeckillExecution;
import com.wgt.enums.SeckillStateEnum;
import com.wgt.exception.DataRewriteException;
import com.wgt.exception.RepeatKillException;
import com.wgt.exception.SeckillCloseException;
import com.wgt.exception.SeckillException;
import com.wgt.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * 业务
 * @author wgt
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    //混淆md5
    private final String slat = "jdkabsfba^%&!#hbs@#$!@123afa>/fa~dasjk";


    private String getMd5(long seckillId){
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null){
            return new Exposer(false,seckillId);
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        if (nowTime.getTime()<startTime.getTime()
                ||nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true,md5,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
    }


    /**
     * 秒杀业务
     * ////使用注解控制事务方法的优点
     * 1.开发团队打成一致的约定，明确标注事务方法的编程风格
     * 2，保证事务方法的执行时间尽可能短，不要穿插其他的网络操作RPC/HTTP请求或者玻璃到事务方法外部
     * 3，不是所有的方法都需要事务，如只有一天修改操作，制度操作不需要事务控制
     * @param seckillId 秒杀商品Id
     * @param userPhone 用户手机号
     * @param md5   加密字符串
     * @return  秒杀结果
     * @throws SeckillException 系统异常
     * @throws RepeatKillException  重复秒杀异常
     * @throws SeckillCloseException    请求关闭活动异常
     */
    @Override
    @Transactional
    public SeckillExecution excuteSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMd5(seckillId))){
            throw new DataRewriteException("secKill data rewrite");
        }
        try {
            //执行秒杀逻辑
            int updateCount = seckillDao.reduceNumber(seckillId, new Date());
            if (updateCount <= 0) {
                //没有更新到记录
                throw new SeckillCloseException("seckill is close");
            } else {
                //记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                //唯一性：seckillId,userPhone
                if (insertCount <= 0) {
                    throw new RepeatKillException("seckill repetkillException");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(1), successKilled);
                }
            }
        }catch (RepeatKillException e1) {
            throw e1;
        }catch (SeckillCloseException e2){
            throw e2;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new SeckillException("seckill inner error:"+e.getMessage(),e);
        }
    }
}
