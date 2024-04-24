package edu.kh.ib.login.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.login.model.dto.Member;

@Mapper
public interface loginMapper {

	Member login(String memberEmail);

	

	

}
