package com.wgt.dao;

import com.wgt.domain.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/4/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao mapper;

    @Test
    public void insertSuccessKilled() throws Exception {
        mapper.insertSuccessKilled(1001,1274463432);
    }

    @Test
    public void queryBuIdWithSeckill() throws Exception {
        SuccessKilled seckill=mapper.queryByIdWithSeckill(1001,1274463432);
        System.out.println(seckill);
    }

}