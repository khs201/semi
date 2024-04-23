package edu.kh.ib.board.model.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	/** 게시판 목록 얻어와서 HTML application scope에 뿌리기
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();
	
	

}
