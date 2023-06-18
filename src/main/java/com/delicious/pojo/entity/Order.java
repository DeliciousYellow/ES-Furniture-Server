package com.delicious.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: ES-furniture
 * @description: 订单表
 * @author: 王炸！！
 * @create: 2023-03-16 12:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")//不同名映射
public class Order {
    @TableId(type = IdType.AUTO)
    private String orderId;

    private String orderCode;

    private String creatTime;
    private String orderState;

    private Integer userId;
    private Integer furnitureId;
    private Integer addressId;

    private Integer orderCount;
    private String orderComment;

}
