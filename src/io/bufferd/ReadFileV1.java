package io.bufferd;

import java.io.FileInputStream;
import java.io.IOException;

import static io.bufferd.BufferedConst.*;

/**
 * InputStream 성능 최적화(1) - 최적화 X
 */
public class ReadFileV1 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startMillis = System.currentTimeMillis();

        int fileSize = 0;
        int data;

        while((data = fis.read()) != -1){
            fileSize++;   
        }
        fis.close();

        long endMillis = System.currentTimeMillis();

        System.out.println("File created : " + FILE_NAME);
        System.out.println("File size : " + fileSize / 1024 / 1024 + "MB"); // 10MB 파일생성
        System.out.println("Time Taken : " + (endMillis - startMillis) + "ms"); // 약 11초 소요
    }
}
