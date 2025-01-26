package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 입출력(2)
 * byte[] 입/출력하기
 */
public class StreamStartMain2 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat", false);
        byte[] input = {65,66,67,68};
        fos.write(input);
        fos.close();

        /**
         * 10 바이트식 읽어들이기.
         * 1. 큰 용량의 파일을 읽을 때, 유리하다. => 조금식 읽어서 처리 후 다시 다음 읽기
         * 2. 작은 용량은 한번에 읽어들이는게 더 편리하다.
         */
        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] buffer = new byte[10];
        int readCount = fis.read(buffer, 0, 10);
        System.out.println(readCount);
        System.out.println(Arrays.toString(buffer));
        fis.close();
    }
}
