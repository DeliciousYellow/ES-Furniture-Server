package com.delicious.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Admin;
import com.delicious.pojo.entity.User;
import com.delicious.service.AdminService;
import com.delicious.service.LoginService;
import com.delicious.service.UserService;
import com.delicious.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-10 02:41
 **/
@RestController
@Api(tags = "登录和注册相关")
@CrossOrigin//允许跨域
public class LoginController {

    @Resource
    private LoginService loginService;
    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;

    @ApiOperation("管理员登录")
    @PostMapping("/AdminLogin")
    public Result AdminLogin(@RequestBody Admin form) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAdminCode,form.getAdminCode());
        Admin admin = adminService.getOne(wrapper);
        //根据工号和密码判断登录
        if(admin==null){
            return Result.fail().setMessage("账号不存在");
        }else if(!form.getAdminPwd().equals(admin.getAdminPwd())){
            //密码错误
            return Result.fail().setMessage("密码错误");
        }
        String token = JwtUtils.getToken(admin.getAdminCode());
        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    @ApiOperation("管理员退出登录")
    @PostMapping("/AdminLogout")
    public Result AdminLogout() {
        return Result.ok();
    }

    @ApiOperation("用户注册")
//    @ResponseBody
    @PostMapping("/Register")
    public String Register(@RequestParam("code") String code, @RequestParam("nickName") String nickName, @RequestParam("avatarUrl") String avatarUrl) {
        //根据code获取openid和SessionKey
        Map<String, String> map = loginService.GetOpenidAndSessionKeyByCode(code);
        String openid = map.get("openid");

        User user = new User();
        user.setUserOpenid(openid);
        user.setNickName(nickName);
        user.setAvatarUrl(avatarUrl);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserOpenid, user.getUserOpenid());
        User one = userService.getOne(wrapper);
        if (one == null) {
            //如果数据库中没有该openid对应的数据，就注册
            return userService.save(user) ? JSON.toJSONString(Result.ok()) : JSON.toJSONString(Result.fail());
        } else {
            //登录
            return "已有账号，前往登录";
        }
    }
}
