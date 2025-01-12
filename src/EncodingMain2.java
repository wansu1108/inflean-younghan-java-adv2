import java.nio.charset.Charset;
import static java.nio.charset.StandardCharsets.*;
import java.util.Arrays;

public class EncodingMain2 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== 영문 ASCII 인코딩 ==");
        test("A", US_ASCII, US_ASCII);
        test("A", US_ASCII, ISO_8859_1); // LATIN-1(ASCII 확장)
        test("A", US_ASCII, EUC_KR); // ASCII 포함
        test("A", US_ASCII, MS949); //  ASCII 포함
        test("A", US_ASCII, UTF_8); //  ASCII 포함
        test("A", US_ASCII, UTF_16BE); // EUC_KR = ASCII 확장

        System.out.println("== 한글 인코딩 - 기본 ==");
        test("가", US_ASCII, US_ASCII); // 한글지원 X 
        test("가", ISO_8859_1, ISO_8859_1); // 한글지원 X
        test("가", EUC_KR, EUC_KR); // 한글 지원
        test("가", MS949, MS949); // MS949(EUC-KR 확장)
        test("가", UTF_8, UTF_8); // 한글 지원
        test("가", UTF_16BE, UTF_16BE); // 한글 지원

        System.out.println("== 한글 인코딩 - 복잡한 문자 ==");
        test("뷁", EUC_KR, EUC_KR); // 자주사용하는 한글 지원
        test("뷁", MS949, MS949); // MS949(EUC-KR 확장, 모든 한글 지원)
        test("뷁", UTF_8, UTF_8); // 모든 한글 지원
        test("뷁", UTF_16BE, UTF_16BE); // 모든 한글 지원

        System.out.println("== 한글 인코딩 - 디코딩이 다른 경우 ==");
        test("가", EUC_KR, MS949); // MS949(EUC-KR 확장)
        test("뷁", MS949, EUC_KR); // MS949는 인코딩 O, EUC_KR 디코딩 X
        test("가", EUC_KR, UTF_8); // 호환 X
        test("가", MS949, UTF_8); // 호환 X, 윈도우 기본 CharSet(MS949) 에서 작성한 문서가, UTF-8을 기본으로 사용하는 OS에서 깨지는 현상
        test("가", UTF_8, MS949); // 호환 X

        System.out.println("== 영문 인코딩 - 디코딩이 다른 경우 ==");
        test("A", EUC_KR, UTF_8);
        test("A", MS949, UTF_8);
        test("A", UTF_8, MS949);
        test("A", UTF_8, UTF_16BE); // UTF-16 호환 X
    }

    private static void test(String text, Charset encodeCharset, Charset decodeCharset) {
        byte[] bytes = text.getBytes(encodeCharset);
        String decodedStr = new String(bytes, decodeCharset);

        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte -> [%s] 디코딩 -> %s\n"
            , text, encodeCharset , Arrays.toString(bytes), bytes.length
            , decodeCharset , decodedStr
        );
    }
}
