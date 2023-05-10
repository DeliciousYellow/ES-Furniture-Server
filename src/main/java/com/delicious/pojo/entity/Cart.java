package com.delicious.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: ES-furniture
 * @description: 购物车表
 * @author: 王炸！！
 * @create: 2023-03-16 12:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_cart")//不同名映射
public class Cart {
    private Integer cartId;
    private Integer userId;
    private String cartFurnitureId;
    private Integer cartCount;
}
