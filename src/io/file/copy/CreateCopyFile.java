package io.file.copy;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateCopyFile {

    public static final int FILE_SIZE = 200 * 1024 * 1024;
    public static final String PATH = "temp/copy.dat";

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        FileOutputStream fos = new FileOutputStream(PATH);

        byte[] bytes = new byte[FILE_SIZE];
        fos.write(bytes);
        fos.close();
        
        long endTime = System.currentTimeMillis();
        
        System.out.println("File Name: " + PATH);
        System.out.println("File Size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time Taken: " + (endTime - startTime) + "ms");
    }
}
