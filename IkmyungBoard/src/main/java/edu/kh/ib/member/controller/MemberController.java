package edu.kh.ib.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.ib.member.model.dto.Member;
import edu.kh.ib.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("member")
@Slf4j
@SessionAttributes("loginMember")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	/** 회원 가입 페이지로 이동
	 * @return
	 */
	@GetMapping("signup")
	public String signupPage() {
		
		
		
		return "member/signup";
	}
	
	
	/** 회원 가입
	 * @param inputMember : 입력된 회원 정보
	 * 				(memberEmail, memberPw, memberNickname, memberTel,memberId) 
	 * @param memberAddress : 입력한 주소 input 3개의 값을 배열로 전달
	 * @param ra : 리다이렉트 시 request scope로 데이터 전달하는 객체
	 * @return
	 */
	@PostMapping("signup")
	public String signup(
			/* @ModelAttribute */ Member inputMember, 
			@RequestParam("memberAddress") String[] memberAddress,
			RedirectAttributes ra
			
			) {
		
		// 회원 가입 서비스 호출
		int result = service.signup(inputMember, memberAddress);
		
		String path = null;
		String message = null;
		if(result > 0) { // 가입 성공 시
			message = inputMember.getMemberNickname() + "님의 가입을 환영합니다";
			
			path = "/";
		} else {
			message = "회원 가입 실패";
			path = "signup";
		}
		
		ra.addFlashAttribute("message", message);
		
		
		return "redirect:" + path;
	}
	
	
	
	
}
