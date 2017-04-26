package com.wgt.handler;

import com.wgt.domain.Seckill;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/4/26.
 */
public class SeckillHandler implements TypeHandler<Seckill> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Seckill seckill, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public Seckill getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Seckill getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Seckill getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
