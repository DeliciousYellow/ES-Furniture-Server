package com.delicious.controller;

import com.alibaba.fastjson.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.User;
import com.delicious.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-03-16 00:37
 **/
@RestController
@Api(tags = "用户操作类")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("无条件查询所有用户")
    @GetMapping("/getuserall")
    public String GetUserALL() {
        List<User> list = userService.list(null);
        return JSON.toJSONString(list);
    }

    @ApiOperation("根据Id查询对应用户")
    @GetMapping("/GetUserById/{id}")
    public Result GetUserById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.ok(user);
    }

    @ApiOperation("根据电话号码查询指定用户")
    @GetMapping("/getuser/{number}")
    public String GetUserByNumber(@PathVariable String number) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserNumber,number);
        User user = userService.getOne(wrapper);

        return JSON.toJSONString(user);
    }

}
