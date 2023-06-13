package com.delicious.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Cart;
import com.delicious.service.CartService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-09 16:14
 **/
@Api("用户购物车相关")
@CrossOrigin
@RestController
public class CartController {

    @Resource
    private CartService cartService;

    @GetMapping("/GetCartByUserId/{userId}")
    public Result GetCartByUserId(@PathVariable Integer userId){
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        List<Cart> list = cartService.list(wrapper.eq(Cart::getUserId, userId));
        return Result.ok(list);
    }
}
