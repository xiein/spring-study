package com.faith.service.impl;

import com.faith.exception.StorageException;
import com.faith.exception.StorageFileNotFoundException;
import com.faith.properties.StorageProperties;
import com.faith.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Author faith
 * @Date 2022/8/27 10:03
 * @Version 1.0
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        //Paths.get(String first, String... more) : 将路径字符串或从路径字符串连接起来的一系列字符串转换为Path
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            //创建一个目录
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialized storage", e);
        }

    }

    @Override
    public void store(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }
            //Path.resolve(Path path) : 将两个路径合并返回
            //Path.normalize() : 消除路径中的 . 或 。。
            //Path.toAbsolutePath() : 返回绝对路径
            Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(file.getOriginalFilename()))).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory.");
            }
            try(InputStream inputStream = file.getInputStream()) {
                //Files.copy(InputStream in, Path target, CopyOption... options) : 将输入流中所有字节复制到文件中，REPLACE_EXISTING： 如果存在就替换
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }


    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filaName) {
        return rootLocation.resolve(filaName);
    }

    @Override
    public Resource loadResource(String fileName) {
        try {
            Path file = load(fileName);
            UrlResource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + fileName, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
