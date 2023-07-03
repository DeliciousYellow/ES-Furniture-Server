package com.delicious.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @program: ES-furniture
 * @description: 家具表
 * @author: 王炸！！
 * @create: 2023-03-16 12:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_furniture")//不同名映射
public class Furniture {
    @TableId(type = IdType.AUTO)//自增主键字段自动赋值给对应的主键属性
    private Integer furnitureId;

    private String furnitureName;
    private BigDecimal furniturePrice;
    private BigDecimal originPrice;
    private Integer furnitureQuantity;
    private Integer salesVolume;

    private String furnitureUrl;
    private String detailedInformation;
}
