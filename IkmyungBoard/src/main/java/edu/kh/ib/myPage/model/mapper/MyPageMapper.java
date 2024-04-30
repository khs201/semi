package edu.kh.ib.myPage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.ib.board.model.dto.Board;
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


	/** 회원 번호로 작성한 게시글 수 조회하기
	 * @param memberNo
	 * @return
	 */
	int getListCount(int memberNo);


	/** 작성한 글의 지정된 페이지 목록 조회
	 * @param memberNo
	 * @param rowBounds
	 * @return
	 */
	List<Board> selectBoardList(int memberNo, RowBounds rowBounds);


	



	

	
	


}
