package com.delicious.interceptor;

import com.alibaba.fastjson.JSON;
import com.delicious.pojo.CodeEnum;
import com.delicious.pojo.Result;
import com.delicious.util.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-06-11 11:37
 **/
@Aspect
@Order(1)
@Component
public class DigestInterceptor {

    @Resource
    private RedisTemplate<Integer, String> redisTemplate;

    @Resource
    private HttpServletRequest request;

    @Around("@annotation(com.delicious.annotation.CheckDigest) || @within(com.delicious.annotation.CheckDigest)")
    public Object CheckDigest(ProceedingJoinPoint joinPoint) throws Throwable {
        String expectedDigest = request.getHeader("X-Digest");
        System.out.println("请求头中的X-Digest：" + expectedDigest);
        StringBuffer stringBuffer = new StringBuffer();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof String) {
                stringBuffer.append(arg);
            } else {
                stringBuffer.append(JSON.toJSONString(arg));
            }
        }
        //从Redis获取密钥，并加入StringBuffer
        Integer userId = (Integer) request.getAttribute("userId");
        String digestSecret = redisTemplate.opsForValue().get(userId);
        stringBuffer.append(digestSecret);

        String requestData = stringBuffer.toString();

        MessageDigest digestAlgorithm = MessageDigest.getInstance("SHA-256");
        byte[] digestBytes = digestAlgorithm.digest(requestData.getBytes());
        String actualDigest = DigestUtils.bytesToHex(digestBytes);
        System.out.println("计算出来的X-Digest：" + actualDigest);
        if (actualDigest.equals(expectedDigest)) {
            System.out.println("====================================验证Digest通过==================================================");
            return joinPoint.proceed();
        } else {
            return Result.fail().setCode(CodeEnum.DIGEST_ERROR.code).setMessage("摘要错误,请求已被终止");
        }
    }
}