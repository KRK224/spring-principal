package spring.principal.core.member;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import spring.principal.core.member.interfaces.MemberRepository;

@Component
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new ConcurrentHashMap<>();

    public MemoryMemberRepository() {
        System.out.println("MemoryMemberRepository.MemoryMemberRepository");
    }

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
