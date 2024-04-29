package edu.kh.project.main.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {

	/** 비번 초기화
	 * @param map
	 * @return result
	 */
	int resetPw(Map<String, Object> map);

	/** 탈퇴 회원 재가입시키기
	 * @param inputNo
	 * @return
	 */
	int resetReg(int inputNo);

	/** 회원 삭제 시키기
	 * @param inputNo
	 * @return
	 */
	int delMem(int inputNo);

}
