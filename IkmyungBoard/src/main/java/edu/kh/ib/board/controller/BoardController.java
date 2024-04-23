package edu.kh.ib.board.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			Model model,
			@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap
			) {
		
		String boardName = service.getBoardName(boardCode);
		
		// 조회 서비스 반환값 저장용
		Map<String, Object> map = null; 
		
		map = service.selectBoardList(boardCode, cp);
		
		
		
		model.addAttribute("boardCode", boardCode);
		model.addAttribute("boardName", boardName);
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
		
		
		return "board/board";
		
	}
	

}
