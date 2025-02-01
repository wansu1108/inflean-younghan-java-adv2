package io.file.text;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ReadTextFileV2 {

    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "abc\n가나다";
        System.out.println("== Write String ==");
        System.out.println(writeString);

        Path path = Path.of(PATH);

        // 파일에 쓰기
        Files.writeString(path, writeString, UTF_8);

        // 파일에서 읽기 - 파일 한번에 다 읽기, 라인 단위로 리스트에 저장
        System.out.println("== Read String ==");
        List<String> lines = Files.readAllLines(path);
        for(int i=0; i<lines.size(); i++) {
            System.out.println((i + 1) + ": " + lines.get(i));
        }

        // 파일에서 읽기 - 파일 라인 단위로 나누어 읽기, 메모리 절약
        System.out.println("== Read String ==");
        Stream<String> lineStream = Files.lines(path, UTF_8);
        lineStream.forEach(System.out::println);
        lineStream.close();
        // System.out.println("== Read String ==");
        // try (Stream<String> lineStream = Files.lines(path, UTF_8);) {
        //     lineStream.forEach(System.out::println);
        // }
    }
}
