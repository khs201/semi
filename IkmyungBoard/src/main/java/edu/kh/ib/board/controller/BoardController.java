package edu.kh.ib.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import edu.kh.ib.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService service;
	
	
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoard(
			@PathVariable("boardCode") int boardCode,
			Model model
			
			) {
		
		String boardName = service.getBoardName(boardCode);
		
		model.addAttribute("boardCode", boardCode);
		model.addAttribute("boardName", boardName);
		
		return "board/board";
		
	}
	

}
