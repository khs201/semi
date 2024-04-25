package edu.kh.ib.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String info(
		@SessionAttribute("loginMember") Member loginMember,
		Model model) {
		
		String memberAddress = loginMember.getMemberAddress();
		
		if(memberAddress != null) {
			
			String[] arr = memberAddress.split("\\^\\^\\^");
			
			model.addAttribute("postcode"      , arr[0]);
			model.addAttribute("address"       , arr[1]);
			model.addAttribute("detailAddress" , arr[2]);
		}
		
		
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
	
	@PostMapping("info")
	public String updateInfo( 	
		Member inputMember,
		@SessionAttribute("loginMember") Member loginMember,
		@RequestParam("memberAddress") String[] memberAddress,
		RedirectAttributes ra
		) {
		
		// inputMember에 로그인한 회원번호 추가
		int memberNo = loginMember.getMemberNo();
		inputMember.setMemberNo(memberNo);
		
		// 회원 정보 수정 서비스 호출
		int result = service.updateInfo(inputMember, memberAddress);
		
		String message = null;
		
		if(result > 0) {
			message = "회원 정보 수정 성공!!";
			
			loginMember.setMemberNickname(inputMember.getMemberNickname());
			
			loginMember.setMemberTel(inputMember.getMemberTel());
			
			loginMember.setMemberAddress(inputMember.getMemberAddress());
		} else {
			message = "회원 정보 수정 실패...";
		}
		
		ra.addFlashAttribute("message", message);	
		
		return "redirect:info";
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
