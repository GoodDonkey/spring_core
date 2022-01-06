package hi.core.discount;

import hi.core.member.Grade;
import hi.core.member.Member;

public class FixedDiscountPolicy implements DiscountPolicy{

    private int discountFixedAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // VIP 면 할인
            return discountFixedAmount;
        } else {
            return 0;
        }
    }
}
