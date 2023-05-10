package com.delicious.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Tag;
import com.delicious.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-12 00:13
 **/
@RestController
@Api(tags = "家具标签类")
public class TagController {
    @Resource
    TagService tagService;

    @ApiOperation("查询所有标签类型")
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

}
