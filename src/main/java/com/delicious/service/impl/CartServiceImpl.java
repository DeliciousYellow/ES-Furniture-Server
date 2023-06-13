package com.delicious.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicious.mapper.CartMapper;
import com.delicious.pojo.entity.Cart;
import com.delicious.service.CartService;
import org.springframework.stereotype.Service;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-09 16:22
 **/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
}
