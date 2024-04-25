package edu.kh.ib.login.model.serivce;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.login.model.mapper.loginMapper;
import edu.kh.ib.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
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
}
	
	
	
	

