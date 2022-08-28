package com.faith.exception;

/**
 * @Author faith
 * @Date 2022/8/28 17:23
 * @Version 1.0
 */
public class StorageFileNotFoundException extends StorageException{

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
