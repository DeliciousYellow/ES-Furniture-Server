package com.delicious.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.delicious.pojo.entity.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-09 10:34
 **/
public interface AddressMapper extends BaseMapper<Address> {
    @Select("SELECT address_region FROM t_address WHERE user_id = #{userId} LIMIT 1")
    Address SelectAddressOneByUserId(@Param("userId") Integer userId);

}
