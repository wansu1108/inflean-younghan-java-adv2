import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Integer> prices = Arrays.asList(new Integer[] {10000, 20000, 60000, 70000, 100000});
        int result = findMainPrice(prices);
        System.out.println(result);
    }

    private static int findMainPrice(List<Integer> prices) {
        int result = 0;

        for(Integer price : prices) {
            boolean insfection = true;
            // 10/3 * price
            int subtotal = 10 * price / 3;
            long minPrice = Math.round(subtotal * -0.5);
            long maxPrice = Math.round(subtotal * 0.5);

            System.out.printf("price = [%s], subtotal = [%s], minPrice = [%s], maxPrice = [%s]\n"
            , price ,subtotal, minPrice, maxPrice);

            for(Integer optionPrice : prices) {

                int subtotal2 = price - optionPrice;

                if(minPrice > subtotal2 || subtotal2 > maxPrice) {
                    insfection = false;
                }
            }

            if(insfection) {
                result = price;
                break;
            }
        }

        if(result <= 0) {
            throw new IllegalArgumentException("만족하는 상품가격을 찾을 수 없습니다.");
        }

        return result;
    }
}
