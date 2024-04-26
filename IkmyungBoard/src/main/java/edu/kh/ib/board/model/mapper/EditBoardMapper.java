package edu.kh.ib.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.ib.board.model.dto.Board;
import edu.kh.ib.board.model.dto.BoardImg;

@Mapper
public interface EditBoardMapper {

	/** 게시글 작성(이미지X)
	 * @param inputBoard
	 * @return
	 */
	int boardInsert(Board inputBoard);

	/** 게시글 이미지 모두 삽입, 다중행 한번에 삽입
	 * @param uploadList
	 * @return
	 */
	int insertUploadList(List<BoardImg> uploadList);

	/** 게시글 부분(제목/내용) 수정
	 * @param inputBoard
	 * @return
	 */
	int boardUpdate(Board inputBoard);

	/** 게시글 이미지 삭제
	 * @param map
	 * @return
	 */
	int deleteImage(Map<String, Object> map);

	/** 이미지 수정
	 * @param img
	 * @return
	 */
	int updateImage(BoardImg img);

	/** 게시글 이미지 삽입(1행)
	 * @param img
	 * @return
	 */
	int insertImage(BoardImg img);
	
	
	
	

}
