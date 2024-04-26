package edu.kh.ib.board.model.service;

import java.util.List;

import edu.kh.ib.board.model.dto.Comment;

public interface CommentService {

	/** 댓글 등록
	 * @param comment
	 * @return
	 */
	int insert(Comment comment);

	/** 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	int delete(int commentNo);

	/** 댓글 수정
	 * @param comment
	 * @return
	 */
	int update(Comment comment);

	/** 댓글 조회
	 * @param boardNo
	 * @return
	 */
	List<Comment> select(int boardNo);


}
