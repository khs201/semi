package edu.kh.project.myPage.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dto.UploadFile;

public interface MyPageService {

	/** 회원 정보 수정
	 * @param inputMember
	 * @param memberAddress
	 * @return result
	 */
	int updateInfo(Member inputMember, String[] memberAddress);

	/** 비번 변경
	 * @param loginMember
	 * @param currentPw
	 * @param newPw
	 * @return
	 */
	int changePw(Member loginMember, String currentPw, String newPw);

	/** 회원 탈퇴
	 * @param loginMember
	 * @param currentPw
	 * @return
	 */
	int secession(Member loginMember, String currentPw);

	String fileUpload1(MultipartFile uploadFile) throws IllegalStateException, IOException ;

	/** 업로드 테스트 2 (+DB)
	 * @param uploadFile
	 * @param memberNo
	 * @return result
	 */
	int fileUpload2(MultipartFile uploadFile, int memberNo) throws IllegalStateException, IOException;

	/** 파일 목록 조회
	 * @return
	 */
	List<UploadFile> fileList();

	/** 여러 파일 업로드
	 * @param aaaList
	 * @param bbbList
	 * @return
	 */
	int fileUpload3(List<MultipartFile> aaaList, List<MultipartFile> bbbList, int memberNo) throws IllegalStateException, IOException;

	/** 프로파일 이미지 변경
	 * @param profileImg
	 * @param memberNo
	 * @return
	 */
	int profile(MultipartFile profileImg, Member loginMember) throws IllegalStateException, IOException;

}
