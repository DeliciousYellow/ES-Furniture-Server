package com.delicious.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicious.mapper.AdminMapper;
import com.delicious.pojo.entity.Admin;
import com.delicious.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-05-09 22:25
 **/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
