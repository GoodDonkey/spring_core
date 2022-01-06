package hi.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
        System.out.println("MemoryMemberRepository saved!");

    }

    @Override
    public Member findById(Long memberId) {
        System.out.println("MemoryMemberRepository findById!");
        return store.get(memberId);
    }

}
