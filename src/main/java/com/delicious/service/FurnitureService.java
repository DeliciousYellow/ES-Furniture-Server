package com.delicious.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.delicious.pojo.entity.Furniture;

import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-03-16 13:10
 **/
public interface FurnitureService extends IService<Furniture> {
    List<Furniture> GetFurnitureByTag(String detail);
}
