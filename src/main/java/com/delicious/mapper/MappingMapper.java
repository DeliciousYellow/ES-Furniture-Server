package com.delicious.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.delicious.pojo.entity.Mapping;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-12 00:02
 **/
public interface MappingMapper extends BaseMapper<Mapping> {

    @Insert("INSERT IGNORE INTO t_mapping VALUES (#{furnitureId}, #{tagId})")
    Boolean InsertByFurnitureIdAndTagId(@Param("furnitureId") Integer furnitureId,@Param("tagId") Integer tagId);

}
