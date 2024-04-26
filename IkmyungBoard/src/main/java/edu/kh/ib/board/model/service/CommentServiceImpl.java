package edu.kh.ib.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.board.model.dto.Comment;
import edu.kh.ib.board.model.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	
	private final CommentMapper mapper;
	
	// 댓글 등록
	@Override
	public int insert(Comment comment) {
		
		return mapper.insert(comment);
	}
	
	// 댓글 삭제
	@Override
	public int delete(int commentNo) {
		
		return mapper.delete(commentNo);
	}
	
	
	@Override
	public int update(Comment comment) {
		
		return mapper.update(comment);
	}
	
	// 댓글 조회
	@Override
	public List<Comment> select(int boardNo) {
		
		return mapper.select(boardNo);
	}
}
