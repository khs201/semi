package edu.kh.ib.login.model.serivce;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.login.model.mapper.loginMapper;
import edu.kh.ib.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class loginServiceImpl implements loginService {

	private final loginMapper mapper;
	
	// 암호화
	private final BCryptPasswordEncoder bcrypt;
	
	
	@Override
	public Member login(Member inputMember) {
		

		Member loginMember = mapper.login(inputMember.getMemberId());
		
		if(loginMember == null) return null;
		
		if( !bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			return null;
		}
		loginMember.setMemberPw(null);
		
		return loginMember;
	}
	
	// 빠른 로그인
	public Member quickLogin(String memberId) {
		
		Member loginMember = mapper.login(memberId);
		Member temp = loginMember;
		
		// 탈퇴 또는 없는 회원
		if(loginMember == null) return null;
		
		// 조회된 비밀번호 null로 변경
		loginMember.setMemberPw(null);
		
		return loginMember;
	}
}
	
	
	
	

