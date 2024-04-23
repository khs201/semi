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


	
	
	
	
	

}
