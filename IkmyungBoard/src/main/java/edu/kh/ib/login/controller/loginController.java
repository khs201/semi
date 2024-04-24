package edu.kh.ib.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.ib.login.model.serivce.loginService;
import edu.kh.ib.member.model.dto.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes({"loginMember"})
@RequiredArgsConstructor
@RequestMapping("login")
public class loginController {

	private final loginService service;
	
	@PostMapping("login")
	public String login(
			Member inputMember,
			RedirectAttributes ra,
			Model model,
			@RequestParam(value = "saveId", required = false) String saveId,
			HttpServletResponse resp
			) {
			
		Member loginMember = service.login(inputMember);
			
		if(loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		
		if(loginMember != null) {
			
			model.addAttribute("loginMember", loginMember);
			
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			cookie.setPath("/");
			
			// 만료 기간 지정
			if(saveId != null) { 
				cookie.setMaxAge(30 *24 * 60 * 60); 
			} else {
				cookie.setMaxAge(0);
			}
			
			// 응답 객체에 쿠키 추가 -> 클라이언트로 전달
			resp.addCookie(cookie);
			
			
		}
		
		return "redirect:/"; // 메인페이지 재요청 
	} 
     
}