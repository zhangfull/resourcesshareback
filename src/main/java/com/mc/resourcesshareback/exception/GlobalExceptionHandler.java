package com.mc.resourcesshareback.exception;

import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mc.resourcesshareback.pojo.Result;
import com.mc.resourcesshareback.utils.LogUtil;

import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice // 让这个类成为全局异常处理器
public class GlobalExceptionHandler {

    // 处理所有运行时异常
    @ExceptionHandler(RuntimeException.class)
    public Result<String> handleRuntimeException(RuntimeException ex) {
        return Result.error(8888 ,ex.getMessage()); // 返回封装的错误信息
    }

    // 处理 @RequestBody 校验失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> "字段【" + error.getField() + "】" + error.getDefaultMessage())
                .collect(Collectors.joining("；"));
        return Result.error(1111 ,"参数校验失败：" + errorMessage);
    }

    // 处理 @RequestParam / @PathVariable / @ModelAttribute 校验失败
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(v -> {
                    String field = v.getPropertyPath().toString();
                    field = field.substring(field.lastIndexOf('.') + 1); // 截取最后的字段名
                    return "字段【" + field + "】" + v.getMessage();
                })
                .collect(Collectors.joining("；"));
        return Result.error(111 ,"参数校验失败：" + errorMessage);
    }

    // 👉 添加对 自定义异常 的处理
    @ExceptionHandler(OperationException.class)
    public Result<String> handleOperationException(OperationException ex) {

        return Result.error(ex.getCode(), "操作失败：" + ex.getMessage());

    }

    // 👉 JPA 异常
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        LogUtil.info(getClass(), "数据完整性错误：{}", ex.getMessage());
        return Result.error(100, "操作失败，请重试");
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public Result<String> handleOptimisticLockFailure(OptimisticLockingFailureException ex) {

        LogUtil.info(getClass(), "数据版本冲突，请重试：{}", ex.getMessage());
        return Result.error(100, "操作失败，请重试");
    }

    @ExceptionHandler(PersistenceException.class)
    public Result<String> handlePersistenceException(PersistenceException ex) {

        LogUtil.info(getClass(), "数据库操作失败：{}", ex.getMessage());
        return Result.error(100, "操作失败，请重试");
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleGeneralException(Exception ex) {

        LogUtil.info(getClass(), "未知错误：{}", ex.getMessage());
        return Result.error(100, "操作失败，请重试");
    }
}
