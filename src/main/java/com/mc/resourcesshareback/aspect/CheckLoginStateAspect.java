package com.mc.resourcesshareback.aspect;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mc.resourcesshareback.pojo.Result;
import com.mc.resourcesshareback.utils.JwtUtil;
import com.mc.resourcesshareback.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CheckLoginStateAspect {

    @Around("@annotation(com.mc.resourcesshareback.interpretation.CheckLoginState)")
    public Object checkSupplierSession(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = null;

        //遍历参数，获取 HttpServletRequest
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                break;
            }
        }
        if (request == null) {
            throw new RuntimeException("身份检查失败：无法获取 HttpServletRequest");
        }
        try {
            //  获取请求头中的 JWT 令牌
            String token = request.getHeader("Authorization");
            System.out.println("请求头 Authorization：" + token);
            Map<String, Object> stringObjectMap = JwtUtil.parseToken(token);
            Long userId = null;
            if (stringObjectMap.get("id") instanceof Integer) {
                if (stringObjectMap.get("id") == null) {
                    throw new RuntimeException("账号异常请重新登陆");
                }
                userId = Long.valueOf((Integer) stringObjectMap.get("id"));  // 将 Integer 转换为 Long
            } else if (stringObjectMap.get("userId") instanceof Long) {
                userId = (Long) stringObjectMap.get("id");
            } else {
                // 处理错误情况
                throw new RuntimeException("userId 的类型不匹配");
            }

            System.out.println("用户ID：" + userId);

            ThreadLocalUtil.set(userId);

        } catch (Exception e) {
            return Result.error(333,"not login");
        }
        return joinPoint.proceed();
    }

    // 访问结束后执行
    @After("@annotation(com.content.interpretation.CheckLoginState)")
    public void afterRequest(JoinPoint joinPoint) {
        // 清理资源或做其他结束时的操作
        ThreadLocalUtil.remove();
        System.out.println("请求已处理完毕，清理资源或做其他操作");
    }

}
