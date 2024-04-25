package edu.kh.ib.board.model.service;

import edu.kh.ib.board.model.dto.Comment;

public interface CommentService {

	/** 댓글 등록
	 * @param comment
	 * @return
	 */
	int insert(Comment comment);


}
