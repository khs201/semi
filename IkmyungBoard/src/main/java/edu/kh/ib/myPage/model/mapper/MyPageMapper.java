package edu.kh.ib.myPage.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.ib.member.model.dto.Member;

@Mapper
public interface MyPageMapper {

	/** 회원 정보 수정
	 * @param inputMember
	 * @return
	 */
	int updateInfo(Member inputMember);


	/** 번호 조회
	 * @param loginMember
	 * @return reuslt
	 */
	String change(Member loginMember);

	/** 비밀번호 변경
	 * @param loginMember
	 * @return result
	 */
	int changePw(Member loginMember);

	/** 비밀번호 조회
	 * @param loginMember
	 * @return
	 */
	String selectPw(Member loginMember);

	/** y로 바꾸기
	 * @param loginMember
	 * @return
	 */
	int logout(Member loginMember);


	/** 프로필 이미지 변경
	 * @param mem
	 * @return
	 */
	int profile(Member mem);

	

	
	


}
