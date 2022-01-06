package hi.core;

import hi.core.member.Grade;
import hi.core.member.Member;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import hi.core.order.Order;
import hi.core.order.OrderService;
import hi.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order = " + order.calucaltePrice());
    }
}
