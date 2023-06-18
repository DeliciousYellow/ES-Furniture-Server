package com.delicious.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicious.mapper.LogMapper;
import com.delicious.mapper.OrderMapper;
import com.delicious.pojo.entity.Log;
import com.delicious.pojo.entity.Order;
import com.delicious.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-18 02:22
 **/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
    @Resource
    private LogMapper logMapper;

    @Override
    public HashMap<String, Object> GetOrderAllPage(Integer page, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(page,pageSize);
        List<Log> logs = logMapper.selectList(null);
        map.put("orders",logs);
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        map.put("total",pageInfo.getTotal());
        return map;
    }
}
