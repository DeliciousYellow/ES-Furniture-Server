package com.delicious.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.annotation.CheckToken;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Admin;
import com.delicious.service.AdminService;
import com.delicious.service.FurnitureService;
import com.delicious.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-05-09 22:27
 **/
@Api(tags = "管理后台相关接口")
@RestController
@CrossOrigin
@CheckToken
@RequestMapping("/Admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private FurnitureService furnitureService;

    @ApiOperation("管理员信息获取")
    @GetMapping("/GetInfo")
    public Result AdminInfo(String token) {
        String adminCode = JwtUtils.getClaimsByToken(token).getSubject();
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAdminCode, adminCode);
//        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
//        wrapper.eq(adminCode,"admin_code");
        Admin admin = adminService.getOne(wrapper);
        return Result.ok(admin);
    }

}
