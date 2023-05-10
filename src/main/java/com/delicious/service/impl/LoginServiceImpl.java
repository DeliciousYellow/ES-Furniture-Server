package com.delicious.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.delicious.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-04-10 02:44
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Map<String, String> GetOpenidAndSessionKeyByCode(String code) {
        Properties properties = new Properties();
        try (InputStream reader = Thread.currentThread().getContextClassLoader().getResourceAsStream("AppInfo.properties");) {
            properties.load(reader);
//            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String AppID = properties.getProperty("AppID");
        String AppSecret = properties.getProperty("AppSecret");
        String url = "https://api.weixin.qq.com/sns/jscode2session?" + "appid=" + AppID + "&secret=" + AppSecret + "&js_code=" + code + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSON.parseObject(forObject);

        HashMap<String, String> map = new HashMap<>();
        map.put("sessionKey", jsonObject.getString("session_key"));
        map.put("openid", jsonObject.getString("openid"));
        return map;
    }
}
