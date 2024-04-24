package edu.kh.ib.email.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.ib.email.model.service.EmailService;
import lombok.RequiredArgsConstructor;

@SessionAttributes({"authKey"})
@Controller
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailController {
	
	private final EmailService service;
	
	@ResponseBody
	@PostMapping("signup")
	public int signup(@RequestBody String email
			// HttpSession session
			) {
		
		String authKey = service.sendEmail("signup", email);

		if (authKey != null) { // 인증번호가 반환되서 돌아옴
							   // == 이메일 보내기 성공
			
			
			// 이메일로 전달한 인증번호를 Session에 올려둠
			// 클래스 상단에 @SessionAttributes 작성
			// ** 오류나서 model 사용 안하고 HttpSession 사용함 **
			// session.setAttribute("authKey", authKey); // request -> session
			
			return 1;
		}
		
		// 이메일 보내기 실패
		return 0;
	}
	
	/** 입력된 인증번호와 Session에 있는 인증 번호를 비교
	 * @param inputAuthKey
	 * @return 
	 */
	@ResponseBody
	@PostMapping("checkAuthKey")
	public int checkAuthKey(
			// 전달받은 JSON을 Map으로 변경
			@RequestBody Map<String, Object> map
			) {
		
		// 입력 받은 이메일, 인증 번호가 DB에 있는지 조회
		// 이메일 있고, 인증 번호 일치 == 1
		// 아니면 0
		return service.checkAuthKey(map);
	} 
	
	
	
	
	
}





























