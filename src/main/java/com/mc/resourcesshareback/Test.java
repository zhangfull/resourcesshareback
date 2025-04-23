package com.mc.resourcesshareback;


import java.util.Map;

import com.mc.resourcesshareback.utils.JwtUtil;

public class Test {

    public String getToken() {
        Map<String, Object> claims = new java.util.HashMap<>();
        claims.put("username", "testUser");
        claims.put("userId", 12345L); // 使用 Long 类型的 userId
        String token = JwtUtil.getToken(claims);
        System.out.println("Generated Token: " + token);
        return token;
    }

    public void parseToken(String token) {
        Map<String, Object> claims = JwtUtil.parseToken(token);
        System.out.println("Parsed Claims: " + claims);
    }

    public void threadlocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("testValue");
        System.out.println("ThreadLocal Value: " + threadLocal.get());
    }

    public boolean isLong(Object obj) {
        return obj instanceof Long;
    }

    public static void main(String[] args) {
        System.out.println("tttt");
    }
}
