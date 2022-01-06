package hi.core.order;

import hi.core.discount.DiscountPolicy;
import hi.core.discount.FixedDiscountPolicy;
import hi.core.member.Member;
import hi.core.member.MemberRepository;
import hi.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixedDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member foundMember = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(foundMember, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
