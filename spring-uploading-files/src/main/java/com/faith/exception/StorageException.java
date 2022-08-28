package com.faith.exception;

/**
 * @Author faith
 * @Date 2022/8/27 10:21
 * @Version 1.0
 * 全局异常处理
 */
public class StorageException extends RuntimeException{

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
