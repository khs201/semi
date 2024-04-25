package edu.kh.ib.board.model.service;

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
	
	
	@Override
	public int insert(Comment comment) {
		
		return mapper.insert(comment);
	}
}
