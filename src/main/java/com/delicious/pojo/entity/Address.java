package com.delicious.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-09 10:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_address")
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer addressId;

    private Integer userId;
    @NotNull
    private String consigneeName;
    private String consigneeNumber;
    private String addressDetail;
}
