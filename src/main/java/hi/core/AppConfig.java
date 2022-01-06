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

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(MemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(MemberRepository(), DiscountPolicy());
    }

    private MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    private DiscountPolicy DiscountPolicy() {
        return new RateDiscountPolicy();
//        return new FixedDiscountPolicy();
    }
}
