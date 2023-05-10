package com.delicious.service;


import java.util.Map;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-10 02:43
 **/

public interface LoginService {
    Map<String,String> GetOpenidAndSessionKeyByCode(String code);
}
