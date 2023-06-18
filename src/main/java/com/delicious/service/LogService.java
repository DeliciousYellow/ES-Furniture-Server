package com.delicious.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.delicious.pojo.entity.Log;

import java.util.HashMap;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-18 02:21
 **/
public interface LogService extends IService<Log> {
    HashMap<String, Object> GetOrderAllPage(Integer page, Integer pageSize);
}
