package com.delicious.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicious.mapper.MappingMapper;
import com.delicious.pojo.entity.Mapping;
import com.delicious.service.MappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-12 00:05
 **/
@Service
public class MappingServiceImpl extends ServiceImpl<MappingMapper, Mapping> implements MappingService {
    @Resource
    private MappingMapper mappingMapper;
    @Override
    public Boolean SaveByFurnitureIdAndTagId(Mapping mapping) {
        return mappingMapper.InsertByFurnitureIdAndTagId(mapping.getFurnitureId(),mapping.getTagId());
    }

}
