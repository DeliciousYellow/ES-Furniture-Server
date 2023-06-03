package com.delicious.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.delicious.annotation.CheckToken;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Mapping;
import com.delicious.pojo.entity.Tag;
import com.delicious.service.MappingService;
import com.delicious.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
        wrapper.eq(Tag::getTagType, type).select(Tag::getTagName);
        List<Tag> list = tagService.list(wrapper);
        return JSON.toJSONString(Result.ok(list));
    }

    //=========================================以下是管理员后台的接口============================================================

    @ApiOperation("管理员查询所有标签信息")
    @GetMapping("/Admin/GetTagAll")
    @CheckToken
    public Result GetTagAll() {
        List<Tag> list = tagService.list(null);
        return Result.ok(list);
    }

    @ApiOperation("管理员根据商品ID查询其拥有的标签")
    @GetMapping("/Admin/GetTagById/{id}")
    @CheckToken
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

    @ApiOperation("管理员添加Tag标签")
    @PostMapping("/Admin/AddTag")
    @CheckToken
    public Result AddTag(@RequestBody Tag tag) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getTagName,tag.getTagName());
        List<Tag> list = tagService.list(wrapper);
        if (list==null){
            //不存在同名标签，直接添加
            boolean save = tagService.save(tag);
            return Result.ok().setMessage("成功添加了"+1+"条数据");
        }else {
            //存在同名标签
            AtomicBoolean flag = new AtomicBoolean(true);
            list.forEach(t->{
                if(t.getTagType().equals(tag.getTagType())){
                    flag.set(false);
                }
            });
            if (flag.get()){
                //如果同名的情况下，类型不同，则添加
                boolean save = tagService.save(tag);
                return Result.ok().setMessage("成功添加了"+1+"条数据");
            }
        }
        return Result.fail().setMessage("已存在相同的标签");
    }

    @ApiOperation("管理员删除Tag标签")
    @DeleteMapping("/Admin/DeleteTag")
    @CheckToken
    public Result DeleteTag(@RequestBody String JsonArrTagId) {
        JSONObject jsonObject = JSON.parseObject(JsonArrTagId);
        String arrTagId = jsonObject.getString("arrTagId");
        JSONArray jsonArray = JSON.parseArray(arrTagId);
        AtomicInteger count = new AtomicInteger(0);
        jsonArray.forEach(tagId ->{
            boolean bool = tagService.removeById((Integer)tagId);
            if (bool){
                //如果成功删除了标签，就需要删除mapping表中所有使用过该标签的映射关系
                LambdaQueryWrapper<Mapping> wrapper = new LambdaQueryWrapper<>();
                mappingService.remove(wrapper.eq(Mapping::getTagId,tagId));
                count.getAndIncrement();
            }
        });
        return Result.ok(count.get()).setMessage("成功删除了"+count.get()+"条数据");
    }

}
