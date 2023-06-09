package com.delicious.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-04 16:23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_picture")//不同名映射
public class Picture {
    @TableId(type = IdType.AUTO)//自增主键字段自动赋值给对应的主键属性
    private Integer pictureId;

    private Integer furnitureId;
    private String pictureUrl;
}