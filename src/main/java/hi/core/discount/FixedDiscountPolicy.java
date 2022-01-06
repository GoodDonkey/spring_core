package hi.core.discount;

import hi.core.member.Grade;
import hi.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("FixedDiscountPolicy")
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
