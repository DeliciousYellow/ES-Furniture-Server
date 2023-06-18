package com.delicious.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicious.mapper.OrderMapper;
import com.delicious.pojo.entity.Furniture;
import com.delicious.pojo.entity.Order;
import com.delicious.service.OrderService;
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
 * @create: 2023-06-17 14:40
 **/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public HashMap<String, Object> GetOrderAllPage(Integer page, Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(page,pageSize);
        List<Order> orders = orderMapper.selectList(null);
        map.put("orders",orders);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        map.put("total",pageInfo.getTotal());
        return map;
    }

}
