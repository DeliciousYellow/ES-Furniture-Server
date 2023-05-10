package com.delicious.pojo.entity;

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
    private String orderId;
    private Integer userId;
    private String orderFurnitureId;
    private Integer orderCount;
    private Date creatTime;
    private String signerName;
    private String signerNumber;
    private String signerAddress;
}
