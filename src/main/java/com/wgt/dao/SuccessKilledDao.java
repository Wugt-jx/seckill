package com.wgt.dao;

import com.wgt.domain.SuccessKilled;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/4/26.
 */
@Mapper
@Component
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    @Insert("insert ignore into success_killed(seckill_id,user_phone) value(#{seckillId},#{userPhone})")
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    @Select("select sk.seckill_id,sk.user_phone,sk.create_time,sk.state," +
            "s.seckill_id,s.name,s.number,s.start_time,s.end_time,s.create_time " +
            "from success_killed sk inner join seckill s on sk.seckill_id = s.seckill_id " +
            "where sk.seckill_id =#{seckillId} and sk.user_phone=#{userPhone}")
    @Results({
            @Result(property = "seckill",column ="seckill_id",one = @One(select = "com.wgt.dao.SeckillDao.queryById")),
            @Result(property = "userPhone",column = "user_phone"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "seckillId",column = "seckill_id")
    })
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
