package hi.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    /* 이 어노테이션이 붙은 클래스는 스캔에 제외한다. */

}
