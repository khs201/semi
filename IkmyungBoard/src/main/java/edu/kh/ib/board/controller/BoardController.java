package edu.kh.ib.board.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.ib.board.model.dto.Board;
import edu.kh.ib.board.model.service.BoardService;
import edu.kh.ib.board.model.dto.BoardImg;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		
		
		
		// 검색이 아닌 경우
		if (paramMap.get("key") == null) {

			// 게시글 목록 조회 서비스 호출
			map = service.selectBoardList(boardCode, cp);

		} else { // 검색인 경우
			
			// boardCode를 paramMap에 추가
			paramMap.put("boardCode", boardCode);
			
			// 검색 서비스 호출
			 map = service.searchBoard(paramMap, cp);
			
		}

		
		
		
		
		
		model.addAttribute("boardCode", boardCode);
		model.addAttribute("boardName", boardName);
		model.addAttribute("pagination", map.get("pagination"));
		model.addAttribute("boardList", map.get("boardList"));
		
		
		return "board/board";
		
	}
	
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public String detailBoard(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			Model model,
			RedirectAttributes ra,
			/* 이거 DTO 없어서 아직 못 넣음 @SessionAttribute(value = "loginMember", required = false) Member loginMember,*/
			HttpServletRequest req,
			HttpServletResponse resp
			) {
		
		String boardName = service.getBoardName(boardCode);
		model.addAttribute("boardName", boardName);
		
		Map<String, Integer> map = new HashMap<>(); 
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		/*        !!!!!!!!!!!!!!!!! 아직 미구현  !!!!!!!!!!!!!!!!!
		 * if(loginMember != null) { // 로그인 상태인 경우에만 memberNo 추가 map.put("memberNo",
		 * loginMember.getMemberNo()); }
		 */
		
		Board board = service.selectOne(map);
		
				String path = null;
				
				if(board == null) { // 조회 결과가 없는 경우
					path = "redirect:/board/" + boardCode; // 목록 재요청
					ra.addFlashAttribute("message", "게시글이 존재하지 않습니다.");
				} else { // 조회 결과가 있을 경우
					
					// 아직 미구현!@!@!@!@!@!@@!@!@!@@!@! 쿠키 머지 안함~!@@!#@#@#@##*********
					/* 쿠키를 이용한 조회수 증가 (시작)*/
					
					// 1. 비회원 또는 로그인한 회원의 글이 아닌 경우
					// (글쓴이를 뺀 다른 사람)
					/*
					 * if (loginMember == null || loginMember.getMemberNo() != board.getMemberNo())
					 * {
					 * 
					 * // 요청에 담겨있는 모든 쿠키 얻어오기 Cookie[] cookies = req.getCookies();
					 * 
					 * Cookie c = null; for(Cookie temp : cookies) {
					 * 
					 * // 요청에 담긴 쿠키에 "readBoardNo"가 존재할 때 if(temp.getName().equals("readBoardNo")) {
					 * c = temp; break; } }
					 * 
					 * int result = 0; // 조회수 증가 결과를 저장할 변수
					 * 
					 * if(c == null) { // "readBoardNo"가 요청 받은 쿠키에 없을 때
					 * 
					 * // 새 쿠키 생성("readBoardNo", [게시글번호]) c = new Cookie("readBoardNo", "[" +
					 * boardNo + "]"); result = service.updateReadCount(boardNo);
					 * 
					 * 
					 * } else { // "readBoardNo"가 요청 받은 쿠키에 존재할 때 // ("readBoardNo",
					 * [2][30][400][2000])
					 * 
					 * // 현재 글을 처음 읽은 경우 if(c.getValue().indexOf("[" + boardNo + "]") == -1) {
					 * 
					 * // 해당 글 번호를 쿠키에 누적 c.setValue(c.getValue() + "[" + boardNo + "]"); result =
					 * service.updateReadCount(boardNo); }
					 * 
					 * 
					 * }
					 * 
					 * // 조회 수 증가 성공 시 if(result > 0) {
					 * 
					 * // 먼저 조회된 board의 readCount 값을 // result 값으로 변환 board.setReadCount(result);
					 * 
					 * // 적용 경로 설정 c.setPath("/"); // "/" 이하 경로 요청 시 쿠키 서버로 전달
					 * 
					 * // 수명 지정 Calendar cal = Calendar.getInstance(); // 싱글톤 패턴 cal.add(cal.DATE,
					 * 1);
					 * 
					 * // 날짜 표기법 변경 객체 (DB의 TO_CHAR()와 비슷) SimpleDateFormat sdf = new
					 * SimpleDateFormat("yyyy-MM-dd");
					 * 
					 * // java.util.Date Date a = new Date(); // 현재 시간
					 * 
					 * Date temp = new Date(cal.getTimeInMillis()); // 다음날 (24시간 후) // 2024-04-15
					 * 12:30:10
					 * 
					 * Date b = sdf.parse(sdf.format(temp)); // 다음날 0시 0분 0초
					 * 
					 * // 다음날 0시 0분 0초 - 현재 시간 long diff = (b.getTime() - a.getTime()) / 1000; // ->
					 * 다음날 0시 0분 0초까지 남은 시간을 초단위로 반환
					 * 
					 * c.setMaxAge((int) diff); // 수명 설정
					 * 
					 * resp.addCookie(c); // 응답 객체를 이용해서 클라이언트에게 전달 }
					 * 
					 * }
					 */
					
					/* 쿠키를 이용한 조회수 증가 (끝) */
					
					
					path = "board/boardDetail";
					
					// board - 게시글 상세조회 +imageList + commentList 
					model.addAttribute("board", board);
					
					
					// 조회된 이미지 목록(imageList)가 있을 경우
					if(!board.getImageList().isEmpty()) {
						
						
						BoardImg thumbnail = null;
						
						// imageList의 0번 인덱스 == 가장 빠른 순서(imgOrder)
						// 이미지 목록의 첫 번째 행이 순서 0 == 썸네일인 경우
						if(board.getImageList().get(0).getImgOrder() == 0) {
							thumbnail = board.getImageList().get(0);
						}
						
						// 썸네일이 있을 때 / 없을 때
						// 출력되는 이미지 시작 인덱스 지정하는 코드
						// (썸네일 제외하고 인덱스 계산)
						model.addAttribute("thumbnail", thumbnail);
						model.addAttribute("start", thumbnail != null ? 1: 0);
						
					}
					
				}
				
				return path;
		
		
	}
	
	
	

}
