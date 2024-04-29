package edu.kh.ib.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	// 게시글 작성
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
	
	
	/** 게시글 수정 화면 전환
	 * @param boardCode : 게시판 종류
	 * @param boardNo : 게시글 번호
	 * @param loginMember : 로그인한 회원이 작성한 글이 맞는지 검사 용도
	 * @param model : 포워드 시 request scope로 값 전달하는 용도
	 * @param ra : 리다이렉트 시 request scope로 값 전달하는 용도
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}/update")
	public String boardUpdate (
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			@SessionAttribute("loginMember") Member loginMember,
			Model model, 
			RedirectAttributes ra
				) {
		
			// 수정 화면에 출력할 제목/내용/이미지 조회
			// -> 게시글 상세 조회
			Map<String, Integer> map = new HashMap<>();
			map.put("boardCode", boardCode);
			map.put("boardNo", boardNo);
	
			// BoardService.selectOne(map) 호출
			Board board = boardService.selectOne(map);
	
			String message = null;
			String path = null;
	
			if (board == null) {
	
				message = "해당 게시글이 존재하지 않습니다.";
				ra.addFlashAttribute("message", message);
	
				path = "redirect:/"; // 메인 페이지
	
			} else if (board.getMemberNo() != loginMember.getMemberNo()) {
				message = "자신이 작성한 글만 수정할 수 있습니다.";
				ra.addFlashAttribute("message", message);
	
				// 해당 글 상세 조회
				path = String.format("redirect:/board/%d/%d", boardCode, boardNo);
			} else {
				path = "board/boardUpdate";
				String boardName = boardService.getBoardName(boardCode);
				model.addAttribute("boardName", boardName);
				model.addAttribute("board", board);
			}
	
			return path;
		}
	
	/** 게시글 수정
	 * @param boardCode : 게시판 종류
	 * @param boardNo : 수정할 게시글 번호
	 * @param inputBoard : 커맨드 객체(제목, 내용) 
	 * @param loginMember : 로그인한 회원 번호 이용(로그인한 회원이 작성자인지 검사)
	 * @param images : 제출된 input type="file" 모든 요소
	 * @param ra : 리다이렉트 시 request scope로 값 전달
	 * @param deleteOrder : 삭제된 이미지 순서가 기록된 문자열 (1,2,3)  *****확인 필요*******
	 * @param querystring : 수정 성공 시 이전 파라미터 유지(cp, 검색어) *****확인 필요*******
	 * @return 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}/update")
	public String boardUpdate(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			@ModelAttribute Board inputBoard,
			@SessionAttribute("loginMember") Member loginMember,
			@RequestParam("images") List<MultipartFile> images,
			RedirectAttributes ra,
			@RequestParam(value="deleteOrder", required = false) String deleteOrder,
			@RequestParam(value="querystring", required = false, defaultValue = "")
			String querystring
			) throws IllegalStateException, IOException {
		
		// 1. 커맨드 객체(inputBoard)에 boardCode, boardNo, memberNo 세팅
		inputBoard.setBoardCode(boardCode);
		inputBoard.setBoardNo(boardNo);
		inputBoard.setMemberNo(loginMember.getMemberNo());
		
		// 2. 게시글 수정 서비스 호출 후 결과 반환받기
		int result = service.boardUpdate(inputBoard, images, deleteOrder);
		
		// 3. 서비스 결과에 따라 응답 제어
		String message = null;
		String path = null;
		
		if(result > 0) {
			message = "게시글이 수정 되었습니다.";
			path = String.format("/board/%d/%d%s", boardCode, boardNo, querystring);
		} else {
			message = "수정 실패";
			path = "update"; // 수정 화면 전환의 상대 경로
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
		// 하나의 요청 주소로 GET, POST 주소를 모두 처리하는 방법
		@RequestMapping(value="{boardCode:[0-9]+}/{boardNo:[0-9]+}/delete",
						method = {RequestMethod.GET, RequestMethod.POST})
		public String boardDelete(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
			@SessionAttribute("loginMember") Member loginMember,
			RedirectAttributes ra
			) {

			Map<String, Integer> map = new HashMap<>();
			map.put("boardCode", boardCode);
			map.put("boardNo", boardNo);
			map.put("memberNo", loginMember.getMemberNo());


			int result = service.boardDelete(map);

			String path = null;
			String message = null;

			if(result > 0) {
				path = String.format("/board/%d", boardCode);
				message = "삭제 되었습니다";
			}else {
				path = String.format("/board/%d/%d?cp=%d", boardCode, boardNo, cp);
				message = "삭제 실패";
			}

			ra.addFlashAttribute("message", message);

			return "redirect:" + path;
		}
	
	
	
	
	
}
