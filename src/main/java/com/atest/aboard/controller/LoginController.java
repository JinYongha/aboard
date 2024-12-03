package com.atest.aboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.atest.aboard.domain.Member;
import com.atest.aboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    // 루트 URL 요청 처리
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login"; // /login URL로 리다이렉트
    }

    // 로그인 페이지 요청 처리
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // 로그인 페이지 템플릿
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {

        // 사용자 정보 확인 (MemberService에서 확인)
        Member member = memberService.authenticate(username, password);

        if (member != null) {
            // 로그인 성공 시 홈 페이지로 이동
            return "redirect:/board/list";
        } else {
            // 로그인 실패 시 오류 메시지
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

}
