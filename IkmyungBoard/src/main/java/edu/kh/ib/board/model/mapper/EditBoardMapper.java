package edu.kh.ib.board.model.mapper;

import java.util.List;

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
	
	
	
	

}
