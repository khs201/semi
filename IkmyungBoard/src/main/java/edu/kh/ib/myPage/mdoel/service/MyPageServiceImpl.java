package edu.kh.ib.myPage.mdoel.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.ib.myPage.mdoel.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	
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
