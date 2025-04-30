package annotation.basic;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import util.MyLogger;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface AnnoElement {
    String value();
    int count() default 0;
    String[] tags() default {};
    
    //MyLogger data(); // 다른 타입은 적용X
    Class<? extends MyLogger> annpData() default MyLogger.class;  // 클래스 정보는 가능
}
