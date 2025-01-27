package io.text;

import static java.nio.charset.StandardCharsets.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static io.text.TextConst.*;

// 문자다루기(3)
// FileWriter, FileReader 사용
public class ReadWriterMainV3 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABCD";
        System.out.println("write String : " + writeString);

        // 파일에 쓰기 - FileWriter는 OutputStreamWriter 상속받고, 생성자에서 FileOutputStream 생성한다.
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        fw.write(writeString);
        fw.close();

        // 파일에서 읽기 - FileReader는 InputStreamReader 상속받고, 생성자에서 FileInputStream 생성한다.
        FileReader fr = new FileReader(FILE_NAME, UTF_8);

        StringBuilder content = new StringBuilder();
        int ch;
        while((ch = fr.read()) != -1) {
            content.append((char)ch);
        }
        fr.close();

        System.out.println("read String : " + content.toString());
    }
}
