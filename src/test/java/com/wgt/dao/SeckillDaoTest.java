package com.wgt.dao;

import com.wgt.domain.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/4/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeckillDaoTest {


    @Autowired
    private SeckillDao mapper;

    @Test
    public void reduceNumber() throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:").parse("2015-11-11 12:39:54");
        mapper.reduceNumber(1001,date);
    }

    @Test
    public void queryById() throws Exception {
        Seckill seckill=mapper.queryById(1001);
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = mapper.queryAll(1,3);
        System.out.println(seckills);
    }

}