package io.bufferd;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.bufferd.BufferedConst.*;

/**
 * OutputStream 성능 최적화(2) - byte[]
 * 시스템 콜 호출 횟수 감소 -> 시스템 성능 증가 , 바이트 배열 사용의 번거로움
 */
public class CreatFileV2 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        long startMillis = System.currentTimeMillis();

        byte[] bytes = new byte[BUFFER_SIZE];

        int bufferdConut = 0;
        
        for(int i=0; i < FILE_SIZE; i++){
            bytes[bufferdConut++] = 65;

            // 버퍼 사이즈만큼 루프 실행 하면 , 파일 쓰기, 버퍼 카운트 초기화
            if(bufferdConut == BUFFER_SIZE){
                fos.write(bytes);
                bufferdConut = 0;
            }
        }
        
        // 내보내지 못한, 데이터가 존재하면 비워주기
        if(bufferdConut > 0) {
            fos.write(bytes);
        }
        
        fos.close();

        long endMillis = System.currentTimeMillis();

        System.out.println("File created : " + FILE_NAME);
        System.out.println("File size : " + FILE_SIZE / 1024 / 1024 + "MB"); // 10MB 파일생성
        System.out.println("Time Taken : " + (endMillis - startMillis) + "ms"); // 약 11초 소요
    }
}
