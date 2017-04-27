package com.wgt.service;

import com.wgt.domain.Seckill;
import com.wgt.dto.Exposer;
import com.wgt.dto.SeckillExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeckillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService service;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckills = service.getSeckillList();
        logger.info("list={}",seckills);
    }

    @Test
    public void getById() throws Exception {
        Seckill seckill = service.getById(1001);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Exposer exposer = service.exportSeckillUrl(1001);
        logger.info("exposer={}",exposer);

        /**
         * exposer=Exposer{
         *  exposed=true,
         *  md5='a2ce70e137438569c9f1c14ee696a1c1',
         *  seckillId=1001,
         *  now=null,
         *  start=null,
         *  end=null}
         */
    }

    @Test
    public void excuteSeckill() throws Exception {
        long id =1001;
        long phone = 110120119L;
        String md5 = "a2ce70e137438569c9f1c14ee696a1c1";
        SeckillExecution seckillExecution = service.excuteSeckill(id,phone,md5);
        logger.info("seckillExecution={}",seckillExecution);


        /**
         * seckillExecution=SeckillExecution{
         * seckillId=1001,
         * state=1,
         * stateInfo='秒杀成功',
         * successKilled=SuccessKilled{
         *      seckill=Seckill{
         *          seckillId=1001,
         *          name='1000元秒杀平板',
         *          number=196,
         *          startTime=Thu Apr 27 15:23:34 CST 2017,
         *          endTime=Fri Apr 28 00:00:00 CST 2017,
         *          createTime=Wed Apr 26 16:46:05 CST 2017},
         *      userPhone=13576729515,
         *      state=-1,
         *      createTime=Thu Apr 27 15:23:34 CST 2017
         *  }
         *}
         */
    }

}