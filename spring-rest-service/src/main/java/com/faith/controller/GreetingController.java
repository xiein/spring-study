package com.faith.controller;

import com.faith.entity.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author faith
 * @Date 2022/8/24 22:01
 * @Version 1.0
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping ("/random")
    public Map<String, Object> random() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> value = new HashMap<>();
        value.put(String.valueOf(counter.incrementAndGet()), 10);
        value.put("quote", "Really loving Spring Boot, makes stand alone Spring apps easy.");
        map.put("type", "success");
        map.put("value", value);
        return map;
    }
}
