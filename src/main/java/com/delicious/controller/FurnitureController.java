package com.delicious.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Furniture;
import com.delicious.service.FurnitureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-11 18:08
 **/

@RestController
@Api(tags = "家具仓库类")
@CrossOrigin
public class FurnitureController {

    @Resource
    private FurnitureService furnitureService;

    @ApiOperation("查询所有家具")
    @GetMapping("/getfurnitureall")
    public String GetFurnitureALL() {
        List<Furniture> list = furnitureService.list(null);
        return JSON.toJSONString(Result.ok(list));
    }

    @ApiOperation("查询某标签下所有家具")
    @GetMapping("/getfurnitureByTag/{detail}")
    public String GetFurnitureByTag(@PathVariable String detail) {
        List<Furniture> furnitures;
        try {
            furnitures = furnitureService.GetFurnitureByTag(detail);
        } catch (Exception e) {
            return JSON.toJSONString(Result.error());
        }
        return JSON.toJSONString(Result.ok(furnitures));
    }

    @ApiOperation("根据Id查询对应家具")
    @GetMapping("/getfurnitureById/{id}")
    public Result GetFurnitureByTag(@PathVariable Integer id) {
        Furniture furniture = furnitureService.getById(id);
        return Result.ok(furniture);
    }

    @ApiOperation("添加家具")
    @PostMapping("/addfurniture")
    public String AddFurniture(Furniture furniture) {
        Boolean isok = furnitureService.save(furniture);
        if (isok) {
            return JSON.toJSONString(Result.ok());
        } else {
            return JSON.toJSONString(Result.fail());
        }
    }

    @ApiOperation("删除家具")
    @DeleteMapping("/deletefurniture")
    public String DeleteFurnitureById(Integer id) {
        LambdaQueryWrapper<Furniture> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Furniture::getFurnitureId, id);
        Boolean isok = furnitureService.remove(wrapper);
        if (isok) {
            return JSON.toJSONString(Result.ok());
        } else {
            return JSON.toJSONString(Result.fail());
        }
    }
}
