package annotation.basic;

import java.util.Arrays;

public class BasicControllerMain {
    public static void main(String[] args) {
        AnnoElement annotation = BasicController.class.getAnnotation(AnnoElement.class);
        
        String value = annotation.value();
        System.out.println("value : " + value);

        int count = annotation.count();
        System.out.println("count : " + count);

        String[] tags = annotation.tags();
        System.out.println("tags : " + Arrays.toString(tags));
    }
}
