package edu.kh.project.main.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.main.model.mapper.MainMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MainServiceImpl implements MainService {

	private final MainMapper mapper;
	private final BCryptPasswordEncoder bcrypt;
	
	
	// 비밀번호 초기화
	@Override
	public int resetPw(int inputNo) {
		
		String pw = "1234";
		String encPw = bcrypt.encode(pw);
		
		Map<String, Object> map = new HashMap<>();
		map.put("inputNo", inputNo);
		map.put("encPw", encPw);
		
		return mapper.resetPw(map);
		
	}
	
	@Override
	public int resetReg(int inputNo) {
		
		return mapper.resetReg(inputNo);
	}
	
	/**
	 * 회원 삭제
	 */
	@Override
	public int delMem(int inputNo) {
		
		return mapper.delMem(inputNo);
	}
	
}
