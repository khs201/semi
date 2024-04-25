package edu.kh.ib.myPage.mdoel.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.member.model.dto.Member;
import edu.kh.ib.myPage.mdoel.mapper.MyPageMapper;
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
	
	
	/*
	 * // 회원 탈퇴
	 * 
	 * @Override public int logout(String memberPw, int memberNo) {
	 * 
	 * String originPw = mapper.selectPw(memberPw);
	 * 
	 * if( !(bcrypt.matches(memberPw, originPw)) ) { return 0; }
	 * 
	 * return mapper.secession(memberNo); }
	 */
}
