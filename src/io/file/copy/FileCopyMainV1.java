package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyMainV1 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream("temp/copy.dat");
        FileOutputStream fos = new FileOutputStream("temp/copy_new.dat");

        byte[] readBytes = fis.readAllBytes(); // 파일에서 읽기
        fos.write(readBytes); // 파일에서 읽기
        fis.close();
        fos.close();
        
        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken: " + (endTime - startTime) + "ms");
    }
}
