package com.example.rabbitmq.mapper;

import com.example.rabbitmq.model.AccountRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author wangjiaxing
 * @Date 2021/5/30
 */
@Mapper
public interface AccountRecordMapper {
    @Insert("insert into account_record(amount,des) values (#{account},#{des})")
    void add(AccountRecord record);
}
