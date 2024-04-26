package edu.kh.ib.myPage.model.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

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

	/** 계정 탈퇴
	 * @param memberPw
	 * @param loginMember
	 * @return
	 */
	int logout(String memberPw, Member loginMember);


	/** 프로필 이미지 변경
	 * @param profileImg
	 * @param loginMember
	 * @return result
	 */
	int profile(MultipartFile profileImg, Member loginMember) throws IllegalStateException, IOException;

	



	
	
	

}
