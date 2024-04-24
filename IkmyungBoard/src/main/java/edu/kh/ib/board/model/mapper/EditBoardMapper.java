package edu.kh.ib.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.ib.board.model.dto.Board;

@Mapper
public interface EditBoardMapper {

	/** 게시글 작성(이미지X)
	 * @param inputBoard
	 * @return
	 */
	int boardInsert(Board inputBoard);
	
	
	
	

}
