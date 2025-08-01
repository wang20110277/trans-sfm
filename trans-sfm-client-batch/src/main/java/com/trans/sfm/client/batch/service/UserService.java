package com.trans.sfm.client.batch.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.sfm.client.batch.entity.User;
import com.trans.sfm.client.batch.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}