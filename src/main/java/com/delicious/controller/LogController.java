package com.delicious.controller;

import com.delicious.annotation.CheckToken;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Log;
import com.delicious.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-18 02:20
 **/
@Api(tags = "日志相关接口")
@RestController
@CrossOrigin
public class LogController {
    @Resource
    private LogService logService;

    @ApiOperation("分页获取所有订单信息")
    @GetMapping("/Admin/GetLogAllPage/{page}/{pageSize}")
    @CheckToken
    public Result GetTablePage(@PathVariable Integer page, @PathVariable Integer pageSize) {
        HashMap<String, Object> map = logService.GetOrderAllPage(page, pageSize);
        return Result.ok(map);
    }
}
