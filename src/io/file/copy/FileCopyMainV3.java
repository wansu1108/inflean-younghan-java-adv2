package io.file.copy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopyMainV3 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        Path source = Path.of("temp/copy.dat");
        Path target = Path.of("temp/copy_new.dat");

        /**
         * 자바에 파일 데이터를 불러오지 않고, 운영체제의 파일 복사 기능을 사용
         * 앞선 예제 : 파일(copy.dat) -> 자바(byte) -> 파일(copy_new.dat)
         * Files.copy() : 파일(copy.dat) -> 파일(copy_new.dat)
         */
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken: " + (endTime - startTime) + "ms");
    }
}
