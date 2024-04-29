package edu.kh.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.main.model.service.MainService;
import lombok.RequiredArgsConstructor;

@Controller // bean 등록
@RequiredArgsConstructor
public class MainController {

	private final MainService service;
	
	
	
	@RequestMapping("/") // "/" 요청 매핑(method 가리지 않음)
	public String mainPage () {
		
		
		return "common/main"; // 포워딩
		
		
	}
	
	
	/** 비번 초기화
	 * @param inputNo
	 * @return result
	 */
	@PutMapping("resetPw")
	@ResponseBody
	public int resetPw(
			@RequestBody int inputNo
			
			) {
		
		
		return service.resetPw(inputNo);
	}
	
	@PutMapping("resetReg")
	@ResponseBody
	public int resetReg(
			@RequestBody int inputNo
			) {
		
		return service.resetReg(inputNo);
		
	}
	
	@DeleteMapping("delMem")
	@ResponseBody
	public int delMem (
			@RequestBody int inputNo
			) {
		
		
		
		return service.delMem(inputNo);
	}

	@GetMapping("loginError")
	public String loginError(RedirectAttributes ra) {
		
		ra.addFlashAttribute("message", "로그인 후 이용해라");
		
		return "redirect:/";
	}
	
	
	
}
