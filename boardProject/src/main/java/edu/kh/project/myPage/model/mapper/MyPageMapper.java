package edu.kh.project.myPage.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dto.UploadFile;

@Mapper
public interface MyPageMapper {

	/** 회원 정보 수정
	 * @param inputMember
	 * @return result
	 */
	int updateInfo(Member inputMember);

	/** 현재 비번 조회
	 * @param loginMember
	 * @return
	 */
	String currPw(Member loginMember);

	/** 비번 변경
	 * @param loginMember
	 * @return
	 */
	int changePw(Member loginMember);

	/** 회원 탈퇴
	 * @param loginMember
	 * @return
	 */
	int secession(Member loginMember);

	/** 파일 정보를 DB에 삽입
	 * @param uf
	 * @return result
	 */
	int insertUploadFile(UploadFile uf);

	/** 파일 목록 조회
	 * @return
	 */
	List<UploadFile> fileList();

	/** 프로필 이미지 변경
	 * @param mem
	 * @return result
	 */
	int profile(Member mem);

}
