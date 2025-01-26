package io.bufferd;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static io.bufferd.BufferedConst.*;

/**
 * InputStream 성능 최적화(3) - BufferedXXX 사용
 * 보조 스트림 BufferedInputStream 사용 , 내부에 byte[] 코드가 존재 한다.
 */
public class ReadFileV3 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        BufferedInputStream bis = new BufferedInputStream(fis, BUFFER_SIZE);
        long startMillis = System.currentTimeMillis();

        int fileSize = 0;
        int data;

        while((data = bis.read()) != -1){
            fileSize++;   
        }
        bis.close();

        long endMillis = System.currentTimeMillis();

        System.out.println("File name : " + FILE_NAME);
        System.out.println("File size : " + fileSize / 1024 / 1024 + "MB"); // 10MB 파일생성
        System.out.println("Time Taken : " + (endMillis - startMillis) + "ms"); // 약 11초 소요
    }
}
