package com.delicious.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicious.mapper.FurnitureMapper;
import com.delicious.pojo.entity.Furniture;
import com.delicious.pojo.entity.Mapping;
import com.delicious.pojo.entity.Tag;
import com.delicious.service.FurnitureService;
import com.delicious.service.MappingService;
import com.delicious.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-11 18:10
 **/
@Service
public class FurnitureServiceImpl extends ServiceImpl<FurnitureMapper, Furniture> implements FurnitureService {

    @Resource
    private MappingService mappingService;
    @Resource
    private TagService tagService;
    @Resource
    private FurnitureMapper furnitureMapper;

    @Override
    public List<Furniture> GetFurnitureByTag(String detail) {
        //根据标签名字查询标签ID
        LambdaQueryWrapper<Tag> t = new LambdaQueryWrapper<>();
        t.eq(Tag::getTagDetail,detail);
        Tag one = tagService.getOne(t);
        //根据映射表查到所有属于该标签的家具id
        LambdaQueryWrapper<Mapping> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Mapping::getTagId, one.getTagId());
        List<Mapping> list = mappingService.list(wrapper);

        ArrayList<Furniture> datalist = new ArrayList<>();
        //遍历每个id查询家具添加到datalist中
        list.forEach(JL->{
            LambdaQueryWrapper<Furniture> wrapperx = new LambdaQueryWrapper<>();
            wrapperx.eq(Furniture::getFurnitureId,JL.getFurnitureId());
            datalist.add(furnitureMapper.selectOne(wrapperx));
        });
        return datalist;
    }
}
