package io.start;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 입출력(4)
 * 1. 메모리 스트림
 * 2. Input 스트림을 추상화한 클래스
 * 3. FileStream 과 사용방법이 동일하다.
 */
public class ByteArrayStreamMain {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] input = {65,66,67};
        baos.write(input);

        ByteArrayInputStream bios = new ByteArrayInputStream(baos.toByteArray());
        byte[] buffer = bios.readAllBytes();
        System.out.println(Arrays.toString(buffer));
    }
}
