package edu.kh.ib.board.model.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	/** 게시판 목록 얻어와서 HTML application scope에 뿌리기
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();

	/** 들어가는 게시판 이름을 게시판 코드로 조회해오기
	 * @param boardCode
	 * @return
	 */
	String getBoardName(int boardCode);
	
	

}
