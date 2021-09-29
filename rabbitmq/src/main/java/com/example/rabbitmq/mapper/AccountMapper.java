package com.example.rabbitmq.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * @Author wangjiaxing
 * @Date 2021/5/30
 */
@Mapper
public interface AccountMapper {
    @Insert(" insert into account(name,account) values (#{name},#{account})")
    void save(@Param("name") String name, @Param("account") BigDecimal account);
    @Update("update account set account = account + #{account} where id = #{id}")
    void add(@Param("id") Integer id,@Param("account") BigDecimal account);
}
