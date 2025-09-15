package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 붙은 필드로 생성자를 만들어 준다
// @Transactional(readOnly = true) 단순 조회(DB 값 수정X)에선 readOnly가 효율적임 (기본값: false)
// 아래 코드에선 조회가 많기 때문에 클래스 레벨에 readOnly = true를 추가하고
// join하는 부분에만 @Transactional을 추가해서 readOnly = false상태로 바꿈
public class MemberService {

//     필드 인젝션 -> 수정하기 힘들다 (주입하기 까다로움)
//    @Autowired
    private final MemberRepository memberRepository;

//     setter 인젝션 -> 런 타임에 의존선 변경 가능(실제로는 할 일이 거의 없음)
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    // -> @RequiredArgsConstructor 로 대체

//    @Autowired // 생성자 인젝션 -> 생성하는 시점에서 완성되므로 중간에 값 변경 불가
//    -> 생성자가 한개인 경우 @Autowired가 없어도 자동으로 만들어 줌
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
