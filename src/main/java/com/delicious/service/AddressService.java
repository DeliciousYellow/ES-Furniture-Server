package com.delicious.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.delicious.pojo.entity.Address;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-09 10:33
 **/
public interface AddressService extends IService<Address> {
    Address GetAddressOneByUserId(Integer userId);
}
