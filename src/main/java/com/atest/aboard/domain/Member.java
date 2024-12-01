package com.atest.aboard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class Member {
    private String id;      // 회원 아이디 (Primary Key)
    private String name;    // 회원 이름
    private String userpw;  // 비밀번호
    private String email;   // 이메일
    private Integer age;    // 나이
    private String gender;  // 성별
    private String contact;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;  // 계정 생성 시간

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;  // 계정 수정 시간
}
