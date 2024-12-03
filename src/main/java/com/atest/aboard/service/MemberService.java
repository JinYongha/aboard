package com.atest.aboard.service;

import com.atest.aboard.domain.Member;
import com.atest.aboard.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private final MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;  // PasswordEncoder 인터페이스로 주입받을 수 있음


    public Member authenticate(String username, String password) {
        // 사용자 정보를 DB에서 가져오기
        Member member = memberMapper.findByUsername(username);

        if (member != null && passwordEncoder.matches(password, member.getUserpw())) {
            return member; // 로그인 성공
        }
        return null; // 로그인 실패
    }

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    // 모든 회원 조회
    public List<Member> getAllMembers() {
        return memberMapper.getAllMembers();
    }

    // 회원 조회
    public Member getMemberById(String id) {
        return memberMapper.getMemberById(id);
    }

    // 회원 등록
    public void insertMember(Member member) {
        memberMapper.insertMember(member);
    }

    // 회원 정보 수정
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    // 회원 삭제
    public void deleteMember(String id) {
        memberMapper.deleteMember(id);
    }
}
