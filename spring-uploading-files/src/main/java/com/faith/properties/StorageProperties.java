package com.faith.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author faith
 * @Date 2022/8/27 10:14
 * @Version 1.0
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * 存储文件路径
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
