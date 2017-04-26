package com.wgt.dao;

import com.wgt.domain.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

@Mapper
@Component
public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    @Update("update seckill " +
            "set number = number-1 " +
            "where seckill_id=#{seckillId} " +
            "and start_time<=#{killTime} " +
            "and end_time>=#{killTime} " +
            "and number>0")
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀对象
     * @param seckillId
     * @return
     */

    @Select("select seckill_id as seckillId,name,number,start_time as startTime,end_time as endTime,create_time as createTime " +
            "from seckill " +
            "where seckill_id=#{seckillId}")
    Seckill queryById(@Param("seckillId") long seckillId);


    /**
     *根据偏移量查询秒杀商品列表
     * @param offet
     * @param limit
     * @return
     */
    @Select("select seckill_id as seckillId,name,number,start_time as startTime,end_time as endTime,create_time as createTime " +
            "from seckill " +
            "order by create_time desc " +
            "limit #{offet},#{limit}")
    List<Seckill> queryAll(@Param("offet") int offet,@Param("limit") int limit);
}
