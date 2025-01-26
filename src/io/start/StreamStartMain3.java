package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 입출력(3)
 * byte[] 입/출력하기
 */
public class StreamStartMain3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat", false);
        byte[] input = {65,66,67,68};
        fos.write(input);
        fos.close();

        /**
         * 파일 전체 읽어들이기.
         * 1. 적은 용량의 파일을 읽어들일 때 유리함.
         * 2. 큰 용량의 파일을 읽을 때 Out of Memory 발생 할 수 있다.
         */
        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] buffer = fis.readAllBytes();
        System.out.println(Arrays.toString(buffer));
        fis.close();
    }
}
