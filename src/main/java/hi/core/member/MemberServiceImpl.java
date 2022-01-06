package hi.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        System.out.println("MemberServiceImpl joined!");
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        System.out.println("MemberServiceImpl findMember!");
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
