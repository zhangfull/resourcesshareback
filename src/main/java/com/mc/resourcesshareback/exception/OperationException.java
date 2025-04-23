package com.mc.resourcesshareback.exception;


//(对象1->对象2==>操作结果)
//"["++"]->["++"]==>"+""
//
public class OperationException extends RuntimeException {
    private final Integer code;
    public OperationException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
