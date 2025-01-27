package io.stream;

import static io.bufferd.BufferedConst.BUFFER_SIZE;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

// 문자다루기(5) - 기타 Stream 소개1
public class PrintStreamEtcMain {
    public static void main(String[] args) throws IOException {
        // 콘솔 입력처럼, 파일에 저장하기~
        FileOutputStream fos = new FileOutputStream("temp/print.txt");
        PrintStream printStream = new PrintStream(fos);
        printStream.println("Hello java!");
        printStream.println(10);
        printStream.println(true);
        printStream.printf("Hello %s", "java!");
        printStream.close();

        // 파일 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader("temp/print.txt");
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);

        String line;
        while((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        br.close();

        System.out.println("======read Strng=======");
        System.out.println(content);
    }   
}
