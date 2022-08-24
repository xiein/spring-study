package com.faith.entity;

/**
 * @Author faith
 * @Date 2022/8/24 21:59
 * @Version 1.0
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
