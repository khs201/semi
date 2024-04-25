package edu.kh.ib.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.ib.board.model.dto.Comment;

@Mapper
public interface CommentMapper {

	int insert(Comment comment);

}
