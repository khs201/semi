package edu.kh.ib.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.ib.board.model.dto.Comment;
import edu.kh.ib.board.model.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {
	
	private final CommentService service;
	
	
	/** 댓글 조회
	 * @param boardNo
	 * @return 
	 */
	// value 속성 : 매핑할 주소
	// produces 속성 : 응답할 데이터의 형식을 지정
	@GetMapping(value="",produces = "application/json")
	public List<Comment> select(@RequestParam("boardNo") int boardNo){
		
		return service.select(boardNo);
		
	}
	
	
	// 댓글 등록
	@PostMapping("")
	public int insert(
			@RequestBody Comment comment) {
		
		return service.insert(comment);
	}
	
	
	/** 댓글 수정
	 * @param comment (번호, 내용)
	 * @return result
	 */
	@PutMapping("")
	public int update(@RequestBody Comment comment) {
		
		return service.update(comment);
	}
	
	
	
	
	/** 댓글 삭제
	 * @param commentNo
	 * @return result
	 */
	@DeleteMapping("")
	public int delete(@RequestBody int commentNo) {
		
		return service.delete(commentNo);
	}
	
	
}
