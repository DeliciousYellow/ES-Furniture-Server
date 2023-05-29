package com.delicious.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.delicious.annotation.AdminInterceptor;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Mapping;
import com.delicious.service.MappingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-12 00:08
 **/
@RestController
@Api(tags = "家具和标签对应关系相关接口")
@CrossOrigin
public class MappingController {

    @Resource
    MappingService mappingService;


    //=========================================以下是管理员后台的接口============================================================
    @ApiOperation("管理员根据{\"furnitureId\":1,\"arrTagId\":[14,1]}添加对应关系")
    @PostMapping("/Admin/SaveMapping")
    @AdminInterceptor
    public Result SaveMapping(@RequestBody String IdAndArrIdJSON) {
        JSONObject jsonObject = JSON.parseObject(IdAndArrIdJSON);
        Integer furnitureId = jsonObject.getInteger("furnitureId");
        JSONArray arrTagId = jsonObject.getJSONArray("arrTagId");

        int count = 0;
        for (Object tagId : arrTagId) {
            Mapping mapping = new Mapping(furnitureId, (Integer) tagId);
            if (mappingService.SaveByFurnitureIdAndTagId(mapping)) {
                count++;
            }
        }
        return Result.ok(count).setMessage("成功储存了" + count + "条数据");
    }

}
