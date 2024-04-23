package edu.kh.ib.login.model.serivce;

import org.springframework.stereotype.Service;

import edu.kh.ib.login.model.dto.Member;

@Service
public interface loginService {

	Member login(Member inputMember);

	
	

}
