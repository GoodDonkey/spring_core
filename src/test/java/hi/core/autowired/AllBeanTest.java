package hi.core.autowired;

import hi.core.AutoAppConfig;
import hi.core.discount.DiscountPolicy;
import hi.core.member.Grade;
import hi.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    @DisplayName("해당 타입의 모든 빈을 가져오는 경우")
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,
                                                                                       DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class); // 이 클래스의 빈은 하나임.
        Member userA = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(userA, 10000, "fixedDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(userA, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    @Test
    @DisplayName("지역변수로 빈 Map 가져오기")
    void findAllBean2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        Member userA = new Member(1L, "userA", Grade.VIP);

        Map<String, DiscountPolicy> policyMap = ac.getBeansOfType(DiscountPolicy.class);
        int discount = policyMap.get("rateDiscountPolicy")
                                .discount(userA, 10000);
        assertThat(discount).isEqualTo(1000);
        // 따로 클래스를 만드나 이렇게 빈을 가져오나 똑같다.
        // 하지만 아래의 Service 클래스의 필드에 Map, List 로 정의해두면 client 단에서 빈에 접근할 수 있다는 의미가 있음.
        // 즉, 어떤 할인 정책을 적용할 지에 대한 선택권을 클라이언트 쪽으로 넘겨줄 수 있다.
    }

    static class DiscountService { // 이 클래스는 컨테이너에 빈으로 등록된다.
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired // 수동 주입: 여기에 필요한 의존관계를 모두 주입받는다.
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        // 이 메서드는 Map 또는 List 에 담긴 다른 빈들을 이용해 계산하는 로직을 가지고 있다.
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
