package com.delicious.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.annotation.AddLog;
import com.delicious.annotation.CheckDigest;
import com.delicious.annotation.CheckToken;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Address;
import com.delicious.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-09 10:35
 **/
@Api("用户收货地址相关接口")
@CrossOrigin
@RestController
public class AddressController {

    @Resource
    private AddressService addressService;

    @ApiOperation("根据用户Id获取地址")
    @GetMapping("/GetAddressByUserId/{userId}")
    public Result GetAddressByUserId(@PathVariable Integer userId) {
        LambdaQueryWrapper<Address> wrapper = new LambdaQueryWrapper<>();
        List<Address> list = addressService.list(wrapper.eq(Address::getUserId, userId));
        return Result.ok(list);
    }

    @ApiOperation("根据用户Id获取一个地址")
    @GetMapping("/GetAddressOneByUserId/{userId}")
    public Result GetAddressOneByUserId(@PathVariable Integer userId) {
        Address address = addressService.GetAddressOneByUserId(userId);
        return Result.ok(address);
    }

    @ApiOperation("添加地址")
    @PostMapping("/AddAddress")
    @Transactional
    @CheckToken
    @CheckDigest
    @AddLog
    public Result AddAddress(@RequestBody Address address) {
        return addressService.save(address) ? Result.ok().setMessage("添加成功") : Result.fail().setMessage("添加失败");
    }
}
