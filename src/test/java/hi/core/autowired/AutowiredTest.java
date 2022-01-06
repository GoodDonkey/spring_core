package hi.core.autowired;

import hi.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    @DisplayName("빈이 없으면 의존관계 주입이 어떻게 될까?")
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                TestBean.class);
    }

    static class TestBean {
        /* Member 클래스는 빈으로 등록되지 않는다.
        * 따라서 의존관계 주입을 원래는 해줄 수 없다. */

        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            // 호출 자체가 안된다.
            System.out.println("noBean1 = " + noBean1);
        }
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            // 호출은 되는데 null 이 주입된다.
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            // 호출은 되는데 Optional.empty 로 들어간다.
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
