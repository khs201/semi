package edu.kh.ib.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.ib.board.model.dto.Comment;

@Mapper
public interface CommentMapper {

	
	int insert(Comment comment);

	int delete(int commentNo);

	int update(Comment comment);

	List<Comment> select(int boardNo);

}
