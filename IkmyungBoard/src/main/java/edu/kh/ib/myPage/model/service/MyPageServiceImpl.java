package edu.kh.ib.myPage.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.member.model.dto.Member;
import edu.kh.ib.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
		
	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {
		
		if( inputMember.getMemberAddress().equals(",,")) {
			
			inputMember.setMemberAddress(null);
		} else {
			String address = String.join("^^^", memberAddress);
			
			inputMember.setMemberAddress(address);
					
		}
		
		
		return mapper.updateInfo(inputMember);
	}
	
	// 비밀번호 변경
	@Override
	public int change(String currentPw, String newPw, Member loginMember) {
		String  change = mapper.change(loginMember);
		
		if(!bcrypt.matches(currentPw , change)) {
			return 0;
		} else 	{
			String encPw = bcrypt.encode(newPw);
			loginMember.setMemberPw(encPw);
			
			return mapper.changePw(loginMember);
		}
		
		

	}
}
	
	
	

