package com.delicious.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.annotation.AddLog;
import com.delicious.annotation.CheckDigest;
import com.delicious.annotation.CheckToken;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Furniture;
import com.delicious.pojo.entity.Mapping;
import com.delicious.service.FurnitureService;
import com.delicious.service.MappingService;
import com.delicious.util.OSSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
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

    @Resource
    private MappingService mappingService;

    @ApiOperation("查询所有家具")
    @GetMapping("/getfurnitureall")
    public Result GetFurnitureALL() {
        List<Furniture> list = furnitureService.list(null);
        return Result.ok(list);
    }

    @ApiOperation("查询某标签下所有家具")
    @GetMapping("/getfurnitureByTag/{detail}")
    public Result GetFurnitureByTag(@PathVariable String detail) {
        List<Furniture> furnitures;
        try {
            furnitures = furnitureService.GetFurnitureByTag(detail);
        } catch (Exception e) {
            return Result.error();
        }
        return Result.ok(furnitures);
    }

    @ApiOperation("根据Id查询对应家具")
    @GetMapping("/getfurnitureById/{id}")
    public Result GetFurnitureByTag(@PathVariable Integer id) {
        Furniture furniture = furnitureService.getById(id);
        return Result.ok(furniture);
    }

    //=========================================以下是管理员后台的接口============================================================

    @ApiOperation("分页获取家具信息")
    @GetMapping("/Admin/GetFurnitureAllPage/{page}/{pageSize}")
    @CheckToken
    public Result GetTablePage(@PathVariable Integer page, @PathVariable Integer pageSize) {
        HashMap<String, Object> map = furnitureService.GetFurnitureAllPage(page, pageSize);
        return Result.ok(map);
    }

    @ApiOperation("管理员添加家具")
    @PostMapping("/Admin/AddFurniture")
    @Transactional
    @AddLog("添加")
    @CheckToken
    public Result AddFurniture(@RequestParam("img") MultipartFile img,
                               @RequestParam("furnitureName") String furnitureName,
                               @RequestParam("originPrice") BigDecimal originPrice,
                               @RequestParam("furniturePrice") BigDecimal furniturePrice,
                               @RequestParam("furnitureQuantity") Integer furnitureQuantity,
                               @RequestParam("detailedInformation") String detailedInformation
    ) {
        String imgurl = null;
        Result result = Result.ok().setMessage("家具添加成功");
        try {
            imgurl = OSSUtils.Upload(img);
        } catch (Exception e) {
            result = Result.fail().setMessage("图片上传失败,但其他信息已成功添加");
        }
        System.out.println(imgurl);
        Furniture furniture = new Furniture(null, furnitureName, furniturePrice, originPrice, furnitureQuantity, imgurl, detailedInformation);
        return furnitureService.save(furniture) ? result : Result.fail().setMessage("家具添加失败");
    }

        @ApiOperation("管理员删除家具")
        @DeleteMapping("/Admin/DeleteFurniture")
        @Transactional
        @AddLog("删除")
        @CheckToken
        public Result DeleteFurnitureById(@RequestBody String jsonString) {
            JSONObject jsonObject = JSON.parseObject(jsonString);
            Integer furnitureId = jsonObject.getInteger("furnitureId");

            Furniture furniture = furnitureService.getById(furnitureId);
            if(!OSSUtils.Delete(furniture.getFurnitureUrl())){
                System.out.println("OSS删除图片失败");
            }
            LambdaQueryWrapper<Furniture> furnitureWrapper = new LambdaQueryWrapper<>();
            furnitureWrapper.eq(Furniture::getFurnitureId, furnitureId);

            LambdaQueryWrapper<Mapping> mappingWrapper = new LambdaQueryWrapper<>();
            mappingWrapper.eq(Mapping::getFurnitureId, furnitureId);
            boolean remove = mappingService.remove(mappingWrapper);

            return furnitureService.remove(furnitureWrapper) ? Result.ok().setMessage("删除家具成功") : Result.fail().setMessage("删除家具失败");
        }

}
