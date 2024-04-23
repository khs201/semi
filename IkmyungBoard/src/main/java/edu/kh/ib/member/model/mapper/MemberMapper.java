package edu.kh.ib.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.ib.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	/** 회원가입 SQL 실행
	 * @param inputMember
	 * @return result
	 */
	int signup(Member inputMember);

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

}
