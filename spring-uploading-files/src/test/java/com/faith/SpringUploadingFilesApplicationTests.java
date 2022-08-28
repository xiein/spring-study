package com.faith;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class SpringUploadingFilesApplicationTests {

    @Test
    void contextLoads() {
        Path path = Paths.get("D:\\test");
        Path newPath = path.resolve("demo");
        System.out.println(newPath);
    }

    @Test
    void normalizeTest() {
        Path path = Paths.get("D:\\.\\..\\test");
        Path newPath = path.resolve("demo").normalize();
        System.out.println(newPath);
    }

    @Test
    void absolutePathTest() {
        Path path = Paths.get("node\\files");
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
    }

    @Test
    void getParentTest() {
        Path path = Paths.get("node\\files");
        Path absolutePath = path.resolve("demo").normalize().toAbsolutePath();
        System.out.println("parent: " + absolutePath.getParent());
        System.out.println(absolutePath);
    }

}
