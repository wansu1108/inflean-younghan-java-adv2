package io.bufferd;

import java.io.FileInputStream;
import java.io.IOException;

import static io.bufferd.BufferedConst.*;

/**
 * InputStream 성능 최적화(2) - byte[]
 * 시스템 콜 호출 횟수 감소 -> 시스템 성능 증가 , 바이트 배열 사용의 번거로움
 */
public class ReadFileV2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startMillis = System.currentTimeMillis();

        int size;
        int fileSize = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        
        while((size = fis.read(buffer)) != -1){
            fileSize += size;
        }

        long endMillis = System.currentTimeMillis();

        System.out.println("File name : " + FILE_NAME);
        System.out.println("File size : " + fileSize / 1024 / 1024 + "MB"); // 10MB 파일읽기
        System.out.println("Time Taken : " + (endMillis - startMillis) + "ms"); // 약 1초 소요
    }
}
