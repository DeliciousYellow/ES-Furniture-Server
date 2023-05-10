package com.delicious.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicious.mapper.TagMapper;
import com.delicious.pojo.entity.Tag;
import com.delicious.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-11 23:53
 **/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
