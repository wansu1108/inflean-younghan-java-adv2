import java.nio.charset.Charset;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain1 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS949 = Charset.forName("MS949");

    public static void main(String[] args) {
        encoding("A", EUC_KR);
        encoding("A", MS949);
        encoding("A", ISO_8859_1);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE);
        System.out.println("== 한 글 ==");
        encoding("가", EUC_KR);
        encoding("가", MS949);
        encoding("가", ISO_8859_1);
        encoding("가", UTF_8);
        encoding("가", UTF_16BE);
    }

    public static void encoding(String text, Charset charset){
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }
}
