package io.text;

import static java.nio.charset.StandardCharsets.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static io.text.TextConst.*;

// 문자다루기(2)
// Write, Reader객체 내부에서, 바이트를 문자열로 변환해 준다.
public class ReadWriterMainV2 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("write String : " + writeString);

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);
        osw.write(writeString);
        osw.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, UTF_8);
        
        StringBuilder content = new StringBuilder();
        int ch;
        while((ch = isr.read()) != -1) {
            content.append((char)ch);
        }
        isr.close();

        System.out.println("read String : " + content.toString());
    }
}
