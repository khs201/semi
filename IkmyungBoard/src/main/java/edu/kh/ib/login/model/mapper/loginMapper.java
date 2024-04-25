package edu.kh.ib.login.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.ib.member.model.dto.Member;

@Mapper
public interface loginMapper {

	Member login(String memberId);

	

	

}
