package com.faith.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @Author faith
 * @Date 2022/8/27 10:00
 * @Version 1.0
 */
public interface StorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filaName);

    Resource loadResource(String fileName);

    void deleteAll();
}
