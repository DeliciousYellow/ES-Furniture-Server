package com.delicious.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicious.mapper.AddressMapper;
import com.delicious.pojo.entity.Address;
import com.delicious.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-09 10:33
 **/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Override
    public Address GetAddressOneByUserId(Integer userId) {
        return addressMapper.SelectAddressOneByUserId(userId);
    }
}
