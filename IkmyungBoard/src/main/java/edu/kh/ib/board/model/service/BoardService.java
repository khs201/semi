package edu.kh.ib.board.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.ib.board.model.dto.Board;

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

	/** 게시글 목록 조회
	 * @param boardCode
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectBoardList(int boardCode, int cp);

	/** 게시글 상세 조회
	 * @param map
	 * @return
	 */
	Board selectOne(Map<String, Integer> map);

	/** 게시판 검색
	 * @param paramMap
	 * @param cp
	 * @return map
	 */
	Map<String, Object> searchBoard(Map<String, Object> paramMap, int cp);

	/** 게시글 좋아요 체크/해제
	 * @param map
	 * @return
	 */
	int boardLike(Map<String, Integer> map);

	
	/** 게시판 목록 얻어와서 HTML application scope에 뿌리기
	 * @return
	 */
	List<Map<String, Object>> selectPopularBoardTypeList();
	
	/** 게시글 인기글
	 * @param boardCode
	 * @return
	 */
	List<Board> selectPopularBoardList(int boardCode);

	Map<String, Object> selectPopularBoardList(int boardCode, int cp);

	
	

}
