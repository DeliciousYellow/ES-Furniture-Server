package com.delicious.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.delicious.annotation.CheckDigest;
import com.delicious.annotation.CheckToken;
import com.delicious.pojo.Result;
import com.delicious.pojo.entity.Cart;
import com.delicious.pojo.entity.Furniture;
import com.delicious.service.CartService;
import com.delicious.service.FurnitureService;
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
 * @create: 2023-06-09 16:14
 **/
@Api("用户购物车相关")
@CrossOrigin
@RestController
public class CartController {

    @Resource
    private CartService cartService;
    @Resource
    private FurnitureService furnitureService;


    @ApiOperation("获取用户购物车信息")
    @GetMapping("/GetCartByUserId/{userId}")
    public Result GetCartByUserId(@PathVariable Integer userId){
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        List<Cart> list = cartService.list(wrapper.eq(Cart::getUserId, userId));
        ArrayList<Object> resultList = new ArrayList<>();
        for (Cart cart : list){
            Furniture byId = furnitureService.getById(cart.getCartFurnitureId());
            HashMap<String, Object> map = new HashMap<>();
            map.put("Cart",cart);
            map.put("Furniture",byId);
            resultList.add(map);
        }
        return Result.ok(resultList);
    }

    @ApiOperation("添加/修改用户购物车信息")
    @PostMapping("/AddCart")
    @CheckToken
    public Result AddCart(@RequestBody Cart cart){
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId,cart.getUserId()).eq(Cart::getCartFurnitureId,cart.getCartFurnitureId());
        Cart one = cartService.getOne(wrapper);
        if (one == null){
            return cartService.save(cart) ? Result.ok().setMessage("添加购物车成功") : Result.fail().setMessage("添加购物车失败");
        }else {
            one.setCartCount(cart.getCartCount());
            return cartService.saveOrUpdate(one) ? Result.ok().setMessage("添加购物车成功") : Result.fail().setMessage("添加购物车失败");
        }
    }

    @ApiOperation("删除用户购物车信息")
    @DeleteMapping("/DeleteCart")
    @CheckToken
    public Result DeleteCart(@RequestBody Cart cart){
        return cartService.removeById(cart.getCartId()) ? Result.ok() : Result.fail();
    }
}
