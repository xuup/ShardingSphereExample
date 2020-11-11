package com.learn.shardingspringboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShardingMapper {

    @Insert("insert into t_order (order_name, order_user, status) values (#{orderName}, #{orderUser}, #{status})")
    public void insert(String orderName, String orderUser, int status);
}
