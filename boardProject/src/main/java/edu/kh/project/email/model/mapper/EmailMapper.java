package edu.kh.project.email.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper {

	/**
	 * @param map
	 * @return
	 */
	int updateAuthKey(Map<String, String> map);

	int insertAuthKey(Map<String, String> map);

	/**
	 * @param map
	 * @return
	 */
	int checkAuthKey(Map<String, Object> map);

}
