package io.file;

import java.io.File;
import java.io.IOException;

public class OldFilePath {
    public static void main(String[] args) throws IOException {
        File path = new File("temp/..");
        System.out.println("path : " + path.getPath());
        // 절대경로
        System.out.println("Absolute Path : " + path.getAbsolutePath());
        // 정규경로
        System.out.println("Canonical Path : " + path.getCanonicalPath());

        File[] files = path.listFiles();
        for(File f : files) {
            System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
        }
    }
}
