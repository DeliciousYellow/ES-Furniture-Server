package com.delicious.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.delicious.annotation.AdminInterceptor;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Mapping;
import com.delicious.pojo.entity.Tag;
import com.delicious.service.MappingService;
import com.delicious.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-12 00:13
 **/
@RestController
@Api(tags = "标签操作相关接口")
@CrossOrigin
public class TagController {
    @Resource
    TagService tagService;

    @Resource
    MappingService mappingService;

    @ApiOperation("查询所有标签的类型")
    @GetMapping("/gettypeall")
    public String GetTypeAll() {
        //LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        // 这里我想去重，LambdaQueryWrapper没有这种用法select("DISTINCT tag_type")
        //所以只能明文写数据库字段名了
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT tag_type");
        List<Tag> list = tagService.list(wrapper);
        return JSON.toJSONString(Result.ok(list));
    }

    @ApiOperation("查询某类型下所有标签")
    @GetMapping("/gettag/{type}")
    public String GetTagByType(@PathVariable String type) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getTagType, type).select(Tag::getTagDetail);
        List<Tag> list = tagService.list(wrapper);
        return JSON.toJSONString(Result.ok(list));
    }

    //=========================================以下是管理员后台的接口============================================================

    @ApiOperation("管理员查询所有标签信息")
    @GetMapping("/Admin/GetTagAll")
    @AdminInterceptor
    public Result GetTagAll() {
        List<Tag> list = tagService.list(null);
        return Result.ok(list);
    }

    @ApiOperation("管理员根据商品ID查询其拥有的标签")
    @GetMapping("/Admin/GetTagById/{id}")
    @AdminInterceptor
    public Result GetTagById(@PathVariable Integer id) {
        LambdaQueryWrapper<Mapping> mappingwrapper = new LambdaQueryWrapper<>();
        mappingwrapper.eq(Mapping::getFurnitureId,id);
        List<Mapping> mappings = mappingService.list(mappingwrapper);

        List<Tag> tags = new ArrayList<>();
        for(Mapping m : mappings){
            tags.add(tagService.getById(m.getTagId()));
        }
        return Result.ok(tags);
    }

}
