package io.bufferd;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.bufferd.BufferedConst.*;

/**
 * OutputStream 성능 최적화(3) - BufferedXXX 사용
 * 보조 스트림 BufferedOutputStream 사용 , 내부에 byte[] 코드가 존재 한다.
 */
public class CreatFileV3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);
        long startMillis = System.currentTimeMillis();
        
        for(int i=0; i < FILE_SIZE; i++){
            bos.write(65);
        }
        bos.close();

        long endMillis = System.currentTimeMillis();

        System.out.println("File created : " + FILE_NAME);
        System.out.println("File size : " + FILE_SIZE / 1024 / 1024 + "MB"); // 10MB 파일생성
        System.out.println("Time Taken : " + (endMillis - startMillis) + "ms"); // 약 11초 소요
    }
}
