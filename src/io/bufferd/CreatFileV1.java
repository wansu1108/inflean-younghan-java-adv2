package io.bufferd;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.bufferd.BufferedConst.*;

/**
 * OutputStream 성능 최적화(1) - 최적화 X
 */
public class CreatFileV1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        long startMillis = System.currentTimeMillis();
        
        for(int i=0; i < FILE_SIZE; i++){
            fos.write(65);
        }
        fos.close();

        long endMillis = System.currentTimeMillis();

        System.out.println("File created : " + FILE_NAME);
        System.out.println("File size : " + FILE_SIZE / 1024 / 1024 + "MB"); // 10MB 파일생성
        System.out.println("Time Taken : " + (endMillis - startMillis) + "ms"); // 약 11초 소요
    }
}
