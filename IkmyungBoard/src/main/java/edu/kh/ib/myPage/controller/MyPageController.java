package edu.kh.ib.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.ib.member.model.dto.Member;
import edu.kh.ib.myPage.mdoel.service.MyPageService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("myPage")
@SessionAttributes({"loginMember"}) 
public class MyPageController {

	private final MyPageService service;
	
	@GetMapping("info")
	public String info() {
		return "myPage/myPage-info";
	}
	
	@GetMapping("profile")
	public String profile() {
		return "myPage/myPage-profile";
	}
	
	@GetMapping("secession")
	public String secession() {
		return "myPage/myPage-secession";
	}
	
	/*
	 * // 회원 탈퇴
	 * 
	 * @PostMapping("secession") public String secession(
	 * 
	 * @RequestParam("memberPw") String memberPw,
	 * 
	 * @SessionAttribute("loginMember") Member loginMember, SessionStatus status,
	 * RedirectAttributes ra) {
	 * 
	 * int memberNo = loginMember.getMemberNo(); int result =
	 * service.logout(memberPw, memberNo);
	 * 
	 * String message = null; String path = null;
	 * 
	 * if(result > 0) { message = "탈퇴 되었습니다"; path = "/"; } else { message =
	 * "비밀번호가 일치하지 않습니다"; path = "/myPage/secession"; } status.setComplete();
	 * 
	 * ra.addFlashAttribute(message);
	 * 
	 * return "redirect:/" + path; }
	 */
}
