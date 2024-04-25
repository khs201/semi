package edu.kh.ib.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.ib.board.model.dto.Board;
import edu.kh.ib.board.model.service.BoardService;
import edu.kh.ib.board.model.service.EditBoardService;
import edu.kh.ib.member.model.dto.Member;
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
			@SessionAttribute("loginMember") Member loginMember,
			@ModelAttribute Board inputBoard,
			@RequestParam("images") List<MultipartFile> images,
			RedirectAttributes ra
			) throws IllegalStateException, IOException {
		
		// boardCode랑 로그인한 회원의 번호를 inputBoard에 세팅
		inputBoard.setBoardCode(boardCode);
		inputBoard.setMemberNo(loginMember.getMemberNo());
		
		// 게시글 작성 서비스 호출
		// -> 성공 시 [상세조회]를 요청할 수 있도록 
		//    삽입된 게시글 번호 반환 받기
		int boardNo = service.boardInsert(inputBoard, images);
		
		
		
		
		
		return "redirect:/board/" + boardCode;
	}
	
	
	
	
	
	
}
