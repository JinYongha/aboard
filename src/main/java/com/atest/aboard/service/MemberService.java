package com.atest.aboard.service;

import com.atest.aboard.domain.Member;
import com.atest.aboard.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

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
