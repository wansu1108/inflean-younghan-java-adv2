package io.start;

import java.io.IOException;
import java.io.PrintStream;
import static java.nio.charset.StandardCharsets.*;

/**
 * 입출력(5)
 * 1. 콘솔 스트림
 * 2. Input 스트림을 추상화한 클래스
 * 3. FileStream 과 사용방법이 동일하다.
 */
public class PrintStreamMain {
    public static void main(String[] args) throws IOException {
        PrintStream printStream = System.out;
        byte[] input = "Hello!\n".getBytes(UTF_8);

        printStream.write(input);
        System.out.println("Print!");
    }
}
