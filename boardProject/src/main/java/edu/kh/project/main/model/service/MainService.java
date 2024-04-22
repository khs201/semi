package edu.kh.project.main.model.service;

public interface MainService {

	/** 비번 초기화
	 * @param inputNo
	 * @return result
	 */
	int resetPw(int inputNo);

	/** 탈퇴 회원 재가입
	 * @param inputNo
	 * @return result
	 */
	int resetReg(int inputNo);

	/** 회원 삭제시키기
	 * @param inputNo
	 * @return
	 */
	int delMem(int inputNo);

}
