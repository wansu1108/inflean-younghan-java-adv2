package io.bufferd;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.bufferd.BufferedConst.*;

/**
 * OutputStream 성능 최적화(4) - write(FILE_SIZE)
 * 파일 사이즈만큼, 한번에 쓰기 -> 버퍼사이즈는 일정 크기 이상 올려가면 성능의 차이가 없음, 최적화 사이즈 4KB,8KB,16KB
 */
public class CreatFileV4 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        long startMillis = System.currentTimeMillis();

        byte[] buffer = new byte[FILE_SIZE];

        for(int i=0; i<FILE_SIZE; i++){
            buffer[i] = 65;
        }
        
        fos.write(buffer);
        fos.close();

        long endMillis = System.currentTimeMillis();

        System.out.println("File created : " + FILE_NAME);
        System.out.println("File size : " + FILE_SIZE / 1024 / 1024 + "MB"); // 10MB 파일생성
        System.out.println("Time Taken : " + (endMillis - startMillis) + "ms"); // 약 11초 소요
    }
}
