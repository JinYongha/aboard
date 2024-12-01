package com.atest.aboard.mapper;

import com.atest.aboard.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    // ID로 회원 조회
    @Select("SELECT * FROM member WHERE id = #{id}")
    Member getMemberById(String id);

    // 회원 등록
    @Insert("INSERT INTO member (id, name, userpw, email, age, gender, created_at, update_dt) " +
            "VALUES (#{id}, #{name}, #{userpw}, #{email}, #{age}, #{gender}, NOW(), NOW())")
    void insertMember(Member member);

    // 회원 정보 수정
    @Update("UPDATE member SET name = #{name}, userpw = #{userpw}, email = #{email}, " +
            "age = #{age}, gender = #{gender}, update_dt = NOW() WHERE id = #{id}")
    void updateMember(Member member);

    // 회원 삭제
    @Delete("DELETE FROM member WHERE id = #{id}")
    void deleteMember(String id);

    // 모든 회원 조회
    @Select("SELECT * FROM member")
    List<Member> getAllMembers();  // 모든 회원을 반환하는 메서드 추가
}
