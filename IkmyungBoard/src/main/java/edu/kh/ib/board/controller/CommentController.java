package edu.kh.ib.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.ib.board.model.dto.Comment;
import edu.kh.ib.board.model.service.CommentService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {
	
	private final CommentService service;
	
	@PostMapping("")
	public String insert(
			@RequestBody Comment comment) {
		
		int temp = service.insert(comment);
		
		return "redirect:/";
	}
	
}
