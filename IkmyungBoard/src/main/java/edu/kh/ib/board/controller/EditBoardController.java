package edu.kh.ib.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.ib.board.model.dto.Board;
import edu.kh.ib.board.model.service.BoardService;
import edu.kh.ib.board.model.service.EditBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("editBoard")
@RequiredArgsConstructor
@Slf4j
public class EditBoardController {

	private final BoardService boardService;
	private final EditBoardService service;
	
	/**
	 * @param boardCode : 게시판 번호
	 * @return board/boardWrite.html
	 */
	@GetMapping("{boardCode:[0-9]+}/write") // /editBoard/${boardCode}/write
	public String boardWrite(
			@PathVariable("boardCode") int boardCode,
			Model model
			
			) {
		
		String boardName = boardService.getBoardName(boardCode);
		model.addAttribute("boardName", boardName);
		
		
		
		return "board/boardWrite";
	}
	
	
	// 아직 이미지 파일 업로드 미구현임
	@PostMapping("{boardCode:[0-9]+}/write")
	public String boardWrite(
			@PathVariable("boardCode") int boardCode,
			@ModelAttribute Board inputBoard,
			RedirectAttributes ra
			) {
		
		
		return null;
	}
	
	
	
	
	
	
}
