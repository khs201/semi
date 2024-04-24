package edu.kh.ib.member.model.service;

import edu.kh.ib.member.model.dto.Member;

public interface MemberService {

	/** 회원가입 서비스
	 * @param inputMember
	 * @param memberAddress
	 * @return result
	 */
	int signup(Member inputMember, String[] memberAddress);

	/** 이메일 중복 검사
	 * @param memberEmail
	 * @return count
	 */
	int checkEmail(String memberEmail);

	/** 닉네임 중복 검사
	 * @param memberNickname
	 * @return
	 */
	int checkNickname(String memberNickname);
	
	/** 전화번호 중복 검사
	 * @param memberTel
	 * @return
	 */
	int checkTel(String memberTel);

	/** 아이디 중복 검사
	 * @param memberId
	 * @return
	 */
	int checkId(String memberId);

}
