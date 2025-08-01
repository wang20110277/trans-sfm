package com.trans.sfm.client.batch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trans.sfm.client.batch.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}