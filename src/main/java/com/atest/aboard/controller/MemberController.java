package com.atest.aboard.controller;

import com.atest.aboard.domain.Member;
import com.atest.aboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 가입 페이지
    @GetMapping("/register")
    public String showRegisterForm() {
        return "member/register";  // user/register.html을 반환
    }

    // 회원 등록 처리
    @PostMapping("/register")
    public String registerMember(@ModelAttribute Member member) {
        memberService.insertMember(member);
        return "redirect:/member/list";  // 회원 목록으로 리다이렉트
    }

    // 회원 목록 페이지
    @GetMapping("/list")
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "member/memberList";  // 회원 목록을 보여주는 템플릿
    }

    // 회원 상세 페이지
    @GetMapping("/{id}")
    public String viewMember(@PathVariable("id") String id, Model model) {
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "member/memberDetail";  // 회원 상세 정보를 보여주는 템플릿
    }

    // 회원 정보 수정 페이지
    @GetMapping("/{id}/edit")
    public String editMemberForm(@PathVariable("id") String id, Model model) {
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "member/memberEdit";  // 회원 수정 페이지 템플릿
    }

    // 회원 정보 수정 처리
    @PostMapping("/{id}/edit")
    public String editMember(@PathVariable("id") String id, @ModelAttribute Member member) {
        memberService.updateMember(member);
        return "redirect:/member/list";  // 수정 후 회원 목록으로 리다이렉트
    }

    // 회원 삭제
    @PostMapping("/{id}/delete")
    public String deleteMember(@PathVariable("id") String id) {
        memberService.deleteMember(id);
        return "redirect:/members/list";  // 삭제 후 회원 목록으로 리다이렉트
    }
}
