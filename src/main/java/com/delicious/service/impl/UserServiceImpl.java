package com.delicious.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicious.mapper.UserMapper;
import com.delicious.pojo.entity.User;
import com.delicious.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-03-16 13:15
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
