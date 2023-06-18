package com.delicious.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-18 02:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_log")
public class Log {
    @TableId(type = IdType.AUTO)
    private Integer logId;
    private Integer adminId;

    private String operationType;
    private String logTime;
}
