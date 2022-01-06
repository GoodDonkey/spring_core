package hi.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    @DisplayName("stateful 한 singleton 클래스 테스트")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A 사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B 사용자가 20000원 주문 : 끼어듬
        int userBPrice = statefulService2.order("userB", 20000);

//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        Assertions.assertThat(statefulService1.getPrice())
//                  .isEqualTo(20000);
    }

    // spring 사용하여 싱글톤으로 만들기 위해 임시로 만든 config
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}