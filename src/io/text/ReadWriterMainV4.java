package io.text;

import static io.text.TextConst.*;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// 문자다루기(4)
// line 읽기, 쓰기
public class ReadWriterMainV4 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABCD\n가나다라";
        System.out.println("==== write String ====");
        System.out.println(writeString);
        
        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);
        bw.write(writeString);
        bw.close();

        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);

        String line;
        while((line = br.readLine()) != null) { // EOF(null)
            content.append(line).append("\n"); // 줄바꿈
        }
        br.close();

        System.out.println("====read String====");
        System.out.print(content);
    }
}
