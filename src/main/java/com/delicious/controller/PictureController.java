package com.delicious.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.annotation.AddLog;
import com.delicious.annotation.CheckToken;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Picture;
import com.delicious.service.PictureService;
import com.delicious.util.OSSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-06 09:45
 **/
@Api(tags = "家具图片相关接口")
@CrossOrigin
@RestController
public class PictureController {
    @Resource
    PictureService pictureService;

    @ApiOperation("根据商品ID查询商品图片")
    @GetMapping("/GetPictureById/{furnitureId}")
    public Result GetPictureById(@PathVariable("furnitureId") Integer furnitureId) {
        LambdaQueryWrapper<Picture> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Picture::getFurnitureId, furnitureId);
        List<Picture> list = pictureService.list(wrapper);
        return Result.ok(list);
    }

    @ApiOperation("根据图片ID添加商品图片")
    @AddLog
    @CheckToken
    @PostMapping("/Admin/AddPictureById")
    public Result AddPictureById(@RequestParam("furnitureId") Integer furnitureId, @RequestParam("picture") MultipartFile picture) {
        String pictureUrl = null;
        try {
            pictureUrl = OSSUtils.Upload(picture);
        } catch (Exception e) {
            return Result.fail().setMessage("图片添加失败");
        }
        Picture p = new Picture(null, furnitureId, pictureUrl);
        return pictureService.save(p) ? Result.ok(p).setMessage("图片添加成功") : Result.fail().setMessage("图片添加失败");
    }

    @ApiOperation("根据图片ID删除商品图片")
    @AddLog
    @CheckToken
    @DeleteMapping("/DeletePictureById")
    public Result DeletePictureById(@RequestParam("pictureId") Integer pictureId) {
        LambdaQueryWrapper<Picture> getwrapper = new LambdaQueryWrapper<>();
        getwrapper.eq(Picture::getPictureId, pictureId);
        Picture one = pictureService.getOne(getwrapper);
        String pictureUrl = one.getPictureUrl();
        if(!OSSUtils.Delete(pictureUrl)){
            System.out.println("OSS删除图片失败");
        }
        LambdaQueryWrapper<Picture> deletewrapper = new LambdaQueryWrapper<>();
        deletewrapper.eq(Picture::getPictureId, pictureId);
        return pictureService.removeById(deletewrapper) ? Result.ok().setMessage("图片删除成功") : Result.fail("图片删除失败");
    }
}
