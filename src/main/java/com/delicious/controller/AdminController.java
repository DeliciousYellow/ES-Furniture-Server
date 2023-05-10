package com.delicious.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Admin;
import com.delicious.service.AdminService;
import com.delicious.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-05-09 22:27
 **/
@Api(tags = "管理员操作相关")
@RestController
@CrossOrigin
public class AdminController {
    @Resource
    private AdminService adminService;

    @ApiOperation("管理员信息获取")
    @GetMapping("/AdminInfo")
    public Result AdminInfo(String token) {
        String adminCode = null;
        try {
            adminCode = JwtUtils.getClaimsByToken(token).getSubject();
        } catch (Exception e) {
            return Result.fail().setMessage("Token无效或过期，请重新登录");
        }
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAdminCode, adminCode);
//        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
//        wrapper.eq(adminCode,"admin_code");
        Admin admin = adminService.getOne(wrapper);
        return Result.ok(admin);
    }

}
