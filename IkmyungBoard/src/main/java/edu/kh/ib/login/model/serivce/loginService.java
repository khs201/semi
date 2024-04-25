package edu.kh.ib.login.model.serivce;

import org.springframework.stereotype.Service;

import edu.kh.ib.member.model.dto.Member;

@Service
public interface loginService {

	Member login(Member inputMember);

	/** 빠른 로그인
	 * @param string
	 * @return loginMember
	 */
	Member quickLogin(String memberId);

	
	

}
