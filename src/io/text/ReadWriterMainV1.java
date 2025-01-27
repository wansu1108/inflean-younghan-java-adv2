package io.text;

import static java.nio.charset.StandardCharsets.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static io.text.TextConst.*;

// 문자다루기(1)
// InputStream, OuputStream을 통해, 문자를 바이트로 변환 후, 바이트를 저장하고 읽는다.
public class ReadWriterMainV1 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC";

        // 문자열 -> byte, UTF_8
        byte[] writeBytes = writeString.getBytes(UTF_8);
        System.out.println("write String : " + writeString);
        System.out.println("write Byte : " + Arrays.toString(writeBytes));

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(writeBytes);
        fos.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        byte[] readBytes = fis.readAllBytes();
        fis.close();

        //byte -> String, UTF_8
        String readString = new String(readBytes, UTF_8);

        System.out.println("read Bytes : " + Arrays.toString(readBytes));
        System.out.println("read String : " + readString);
    }
}
