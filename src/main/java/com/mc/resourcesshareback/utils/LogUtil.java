package com.mc.resourcesshareback.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static void info(Class<?> clazz, String message, Object... args) {
        getLogger(clazz).info(message, args);
    }

    public static void error(Class<?> clazz, String message, Throwable t) {
        getLogger(clazz).error(message, t);
    }

}

