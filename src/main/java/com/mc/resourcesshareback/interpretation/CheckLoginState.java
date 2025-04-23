package com.mc.resourcesshareback.interpretation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // 1️⃣ 指定注解的作用范围（只能用于方法上）
@Retention(RetentionPolicy.RUNTIME)  // 2️⃣ 运行时保留（可通过反射或 AOP 读取）
public @interface CheckLoginState {
}
