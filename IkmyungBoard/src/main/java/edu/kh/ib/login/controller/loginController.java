package edu.kh.ib.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
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
			
			// 아이디 저장
			Cookie cookie = new Cookie("saveId", loginMember.getMemberId());
			
			// 클라이언트가 어떤 요청을 할 떄 쿠키가 첨부될지 지정
			cookie.setPath("/");
			 
			// 만료 기간 지정
			if(saveId != null) { // 아이디 저장 시 체크
				cookie.setMaxAge(30 *24 * 60 * 60); 
			} else {
				cookie.setMaxAge(0);
			}
			
			// 응답 객체에 쿠키 추가 -> 클라이언트로 전달
			resp.addCookie(cookie);
			
		}
		
		return "redirect:/"; // 메인페이지 재요청 
	} 
	
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
				
	}
	
	
	
	/** 빠른로그인
	 * @param memberId
	 * @param model
	 * @param ra
	 * @return
	 */
	@GetMapping("quickLogin")
	public String quickLogin(
			@RequestParam("memberId") String memberId,
			Model model,
			RedirectAttributes ra) {
		
		Member loginMember = service.quickLogin(memberId);
		
		if(loginMember == null) {
			ra.addFlashAttribute("message","해당 아이디 회원이 존재하지 않습니다.");
		}else {
			model.addAttribute("loginMember",loginMember);
		}
		
		return "redirect:/";
	}
	
}



















