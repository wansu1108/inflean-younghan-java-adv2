import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

public class AvaliableCharSet {
    public static void main(String[] args) {
        // 이용가능한 모든 CharSet 자바 + OS
        SortedMap<String, Charset> availableCharSet =  Charset.availableCharsets();
        
        for(String key : availableCharSet.keySet()) {
            System.out.println(key + " = " + availableCharSet.get(key));
        }

        // UTF-8 문자로 조회
        Charset charset1 = Charset.forName("UTF-8");
        System.out.println("charset1 = " + charset1.toString());
        
        // UTF-8 상수로 조회
        System.out.println("charset2 = " + StandardCharsets.UTF_8.toString());

        // 시스템 기본 CharSet 조회
        Charset defaultCharSet = Charset.defaultCharset();
        System.out.println("defaultCharSet = " + defaultCharSet.toString());
    }
}
