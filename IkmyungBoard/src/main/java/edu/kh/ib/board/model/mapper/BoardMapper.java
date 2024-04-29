package edu.kh.ib.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.ib.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	/**  게시판 목록 얻어와서 HTML application scope에 뿌리기 
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();

	/** 게시판 이름 조회해오기(보드코드로)
	 * @param boardCode
	 * @return
	 */
	String getBoardName(int boardCode);

	/** 게시글 수를 조회
	 * @param boardCode
	 * @return
	 */
	int getListCount(int boardCode);

	/** 특정 게시판의 지정된 페이지 목록 조회
	 * @param boardCode
	 * @param rowBounds
	 * @return
	 */
	List<Board> selectBoardList(int boardCode, RowBounds rowBounds);

	/** 게시글 상세 조회
	 * @param map
	 * @return
	 */
	Board selectOne(Map<String, Integer> map);

	/** 검색 조건이 맞는 게시글 수 조회
	 * @param paramMap
	 * @return count
	 */
	int getSearchCount(Map<String, Object> paramMap);

	/** 검색 결과 목록 조회
	 * @param paramMap
	 * @param rowBounds
	 * @return boardList 
	 */
	List<Board> selectSearchBoard(Map<String, Object> paramMap, RowBounds rowBounds);

	/** 좋아요 체크 해제
	 * @param map
	 * @return
	 */
	int deleteBoardLike(Map<String, Integer> map);

	/** 좋아요 추가
	 * @param map
	 * @return
	 */
	int insertBoardLike(Map<String, Integer> map);

	/** 게시글 좋아요 갯수 조회
	 * @param integer
	 * @return
	 */
	int selectLikeCount(Integer integer);

	/** 게시글 인기글
	 * @param boardCode
	 * @return
	 */
	List<Board> selectPopularBoardList(int boardCode);


	
	
	
	
	

}
