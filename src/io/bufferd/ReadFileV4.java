package io.bufferd;

import java.io.FileInputStream;
import java.io.IOException;

import static io.bufferd.BufferedConst.*;

/**
 * InputStream 성능 최적화(4) - readAllBytes
 * 전체 바이트 한번에 읽기 -> 쓰기와 마찬가지로, 성능 최적화 사이즈가 존재(4KB,8KB,16KB), 버퍼 사이즈를 늘려도 성능이 좋아지지 않음
 */
public class ReadFileV4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startMillis = System.currentTimeMillis();

        byte[] buffer = fis.readAllBytes();

        fis.close();

        long endMillis = System.currentTimeMillis();

        System.out.println("File name : " + FILE_NAME);
        System.out.println("File size : " + buffer.length / 1024 / 1024 + "MB"); // 10MB 파일생성
        System.out.println("Time Taken : " + (endMillis - startMillis) + "ms"); // 약 11초 소요
    }
}
