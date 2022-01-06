package hi.core;

import hi.core.discount.DiscountPolicy;
import hi.core.discount.FixedDiscountPolicy;
import hi.core.discount.RateDiscountPolicy;
import hi.core.member.MemberRepository;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import hi.core.member.MemoryMemberRepository;
import hi.core.order.OrderService;
import hi.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("AppConfig.discountPolicy");
        return new RateDiscountPolicy();
//        return new FixedDiscountPolicy();
    }
}
