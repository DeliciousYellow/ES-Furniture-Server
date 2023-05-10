package com.delicious.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: ES-furniture
 * @description: 用户表
 * @author: 王炸！！
 * @create: 2023-03-16 12:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")//不同名映射
public class User {
    @TableId(type = IdType.AUTO)//自增主键字段自动赋值给对应的主键属性
    private Integer userId;

    private String userOpenid;
    private String nickName;
    private String avatarUrl;

    private String userNumber;
    private String userName;
    private String userPwd;
}
