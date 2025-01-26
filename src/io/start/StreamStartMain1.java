package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 입출력(1)
 * 입출력 기본 예시
 */
public class StreamStartMain1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat", false);
        fos.write(65);
        fos.write(66);
        fos.write(67);
        fos.close();
        
        FileInputStream fis = new FileInputStream("temp/hello.dat");
        // System.out.println(fis.read());
        // System.out.println(fis.read());
        // System.out.println(fis.read());
        // System.out.println(fis.read());
        int data;
        while ((data = fis.read()) != -1) {
            System.out.println(data);
        }
        fis.close();
    }
}
