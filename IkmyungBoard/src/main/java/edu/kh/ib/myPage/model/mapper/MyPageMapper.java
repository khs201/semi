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

	
	/**
	 * 회원탈퇴
	 * 
	 * @param memberNo
	 * @return result
	 *//*
		 * int secession(int memberNo);
		 */
}
