package com.delicious.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.annotation.CheckToken;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Furniture;
import com.delicious.pojo.entity.Order;
import com.delicious.service.FurnitureService;
import com.delicious.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-17 14:41
 **/
@Api(tags = "用户订单类")
@CrossOrigin
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private FurnitureService furnitureService;

    @ApiOperation("根据用户ID查询订单")
    @GetMapping("/GetOrderByUserId/{userId}")
    @CheckToken
    public Result GetOrderByUserId(@PathVariable Integer userId) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId, userId);
        List<Order> list = orderService.list(wrapper);
//        HashMap<String, List<Order>> map = new HashMap<>();
        ArrayList<List<Order>> lists = new ArrayList<>();
        for (Order order : list) {
            boolean found = false;
            for (List<Order> orderList : lists) {
                if (orderList.get(0).getOrderCode().equals(order.getOrderCode())) {
                    // 如果已存在以 OrderCode 为键的列表，则将当前 order 添加到该列表中
                    orderList.add(order);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // 如果不存在以 OrderCode 为键的列表，则创建一个新的列表，并将当前 order 添加到列表中
                List<Order> orderList = new ArrayList<>();
                orderList.add(order);
                lists.add(orderList);
            }
        }
        return Result.ok(lists);
    }

    @ApiOperation("根据用户ID添加订单")
    @PostMapping("/AddOrderByUserId")
    @CheckToken
    public Result GetOrderByUserId(@RequestBody Order order) {
        boolean ok = orderService.save(order);
        //添加订单之后商品库存应该对应减少。
        if (ok) {
            Furniture furniture = furnitureService.getById(order.getFurnitureId());
            furniture.setFurnitureQuantity(furniture.getFurnitureQuantity() - order.getOrderCount());
            furniture.setSalesVolume(furniture.getSalesVolume() + order.getOrderCount());
            furnitureService.updateById(furniture);
        }
        return ok ? Result.ok().setMessage("添加成功") : Result.fail("添加失败");
    }

    //==================================================================================================================

    @ApiOperation("查询所有订单")
    @GetMapping("/GetOrderAll")
    public Result GetOrderAll() {
        List<Order> list = orderService.list(null);
        return Result.ok(list);
    }

    @ApiOperation("分页获取所有订单信息")
    @GetMapping("/Admin/GetOrderAllPage/{page}/{pageSize}")
    @CheckToken
    public Result GetOrderAllPage(@PathVariable Integer page, @PathVariable Integer pageSize) {
        HashMap<String, Object> map = orderService.GetOrderAllPage(page, pageSize);
        return Result.ok(map);
    }

}
