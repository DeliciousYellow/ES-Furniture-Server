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
 * @create: 2023-05-09 22:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_admin")//不同名映射
public class Admin {
    @TableId(type = IdType.AUTO)//自增主键字段自动赋值给对应的主键属性
    private Integer adminId;

    private String adminCode;

    private String adminName;
    private String adminAvatarUrl;

    private String adminPwd;
}
