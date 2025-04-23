package com.mc.resourcesshareback.utils;

public class ThreadLocalUtil {

    // 参数化 ThreadLocal 为 Object 类型，支持存任意值
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    // 泛型方法，通用取值
    @SuppressWarnings("unchecked")
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    // 通用 set
    public static void set(Object object) {
        THREAD_LOCAL.set(object);
    }

    // 删除
    public static void remove() {
        THREAD_LOCAL.remove();
    }

    // ✅ 强制返回 Long 类型（安全转换）
    public static Long getLongId() {
        Object value = THREAD_LOCAL.get();
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            return Long.parseLong((String) value);
        } else if (value != null) {
            throw new RuntimeException("ThreadLocal 中的值不能转换为 Long 类型: " + value.getClass());
        } else {
            return null;
        }
    }
}
