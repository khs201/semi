package edu.kh.ib.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

	/**  게시판 목록 얻어와서 HTML application scope에 뿌리기 
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();


	
	
	
	
	

}
