package com.delicious.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.annotation.AdminInterceptor;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Admin;
import com.delicious.pojo.entity.Furniture;
import com.delicious.service.AdminService;
import com.delicious.service.FurnitureService;
import com.delicious.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-05-09 22:27
 **/
@Api(tags = "管理员操作相关")
@RestController
@CrossOrigin
@AdminInterceptor
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

    @ApiOperation("获取家具表格接口")
    @GetMapping("/GetTable")
    public Result getTableData() {
        List<Furniture> list = furnitureService.list(null);
        return Result.ok(list);
    }

}
