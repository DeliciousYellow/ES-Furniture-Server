package com.delicious.interceptor;

import com.delicious.pojo.CodeEnum;
import com.delicious.pojo.Result;
import com.delicious.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: ES-furniture
 * @description:
 * @author: 王炸！！
 * @create: 2023-05-21 17:54
 **/
@Aspect
@Component
public class AdminInterceptor {
    @Resource
    private HttpServletRequest request;

    //@within匹配带有指定注解的类
    //@annotation匹配带有指定注解的方法
    //@Before不能阻止方法的执行
    @Around("@annotation(com.delicious.annotation.CheckToken) || @within(com.delicious.annotation.CheckToken)")
    public Object CheckToken(ProceedingJoinPoint joinPoint) throws Throwable {
        String token = request.getHeader("X-Token");
        System.out.println("请求头中的X-Token：" + token);
        System.out.println("====================================验证了token==================================================");
        //拿到AdminCode可以在这里添加该管理员的行为日志
//        String AdminCode = JwtUtils.getClaimsByToken(token).getSubject();
        try {
            Claims claimsByToken = JwtUtils.getClaimsByToken(token);
        } catch (ExpiredJwtException e) {
            //JWT验证过期的情况
            return Result.fail().setCode(CodeEnum.TOKEN_TIMEOUT.code).setMessage("令牌过期，请重新登陆");
        } catch (SignatureException | MalformedJwtException e) {
            //JWT签名验证失败的情况|处理 JWT 格式错误的情况
            return Result.fail().setCode(CodeEnum.TOKEN_ERROR.code).setMessage("令牌错误，请重新登陆");
        } catch (Exception e) {
            return Result.error().setMessage(e.getMessage());
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(com.delicious.annotation.AddLog)")
    public Result AddLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        @Addlog注解加一个value存操作类型，添加，删除，修改。
//        分3中情况来处理日志记录
        System.out.println(joinPoint.getThis());
        System.out.println("======================================添加了Log================================================");

        //重新接收目标方法的返回值
        Result result = (Result) joinPoint.proceed();

        System.out.println(result);
        return result;
    }
}
