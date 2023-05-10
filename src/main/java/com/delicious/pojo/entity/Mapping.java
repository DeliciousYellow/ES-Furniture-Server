package com.delicious.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-11 23:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_mapping")//不同名映射
public class Mapping {
    private Integer tagId;
    private Integer furnitureId;
}