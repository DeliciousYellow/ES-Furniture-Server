package com.delicious.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.annotation.CheckToken;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Address;
import com.delicious.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/GetAddressByUserId/{userId}")
    public Result GetAddressByUserId(@PathVariable Integer userId){
        LambdaQueryWrapper<Address> wrapper = new LambdaQueryWrapper<>();
        List<Address> list = addressService.list(wrapper.eq(Address::getUserId, userId));
        return Result.ok(list);
    }

    @PostMapping("/AddAddress")
    @CheckToken
    public Result AddAddress(@RequestBody Address address){
        return addressService.save(address) ? Result.ok().setMessage("添加成功") : Result.fail().setMessage("添加失败");
    }
}
