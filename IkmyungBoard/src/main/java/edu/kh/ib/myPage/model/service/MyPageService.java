package edu.kh.ib.myPage.model.service;

import edu.kh.ib.member.model.dto.Member;

public interface MyPageService {

	/** 회원 정보 수정
	 * @param inputMember
	 * @param memberAddress
	 * @return result
	 */
	int updateInfo(Member inputMember, String[] memberAddress);

	/** 비밀번호 변경
	 * @param currentPw
	 * @param newPw
	 * @param loginMember
	 * @return result
	 */
	int change(String currentPw, String newPw, Member loginMember);

	

	
	
	

}
