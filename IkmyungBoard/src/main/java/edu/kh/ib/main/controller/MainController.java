package edu.kh.ib.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.ib.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final BoardService service;
	
	
	@RequestMapping("/")
	public String mainPage(
			Model model
			) {
		
		int cp = 1; // 첫 페이지의 게시글
        int limit = 5; // 각 게시판에서 가져올 게시글 수
        int[] boardCodes = {1, 2, 3, 4, 5, 6}; // 게시판 코드 배열

        List<Map<String, Object>> list = new ArrayList<>();
        
        for (int boardCode : boardCodes) {
            Map<String, Object> boardData = service.selectBoardList(boardCode, cp, limit);
//            model.addAttribute("boardData" + boardCode, boardData.get("boardList"));
//            model.addAttribute("boardName" + boardCode, boardData.get("boardName"));
            list.add(boardData);
            
        }
        
        model.addAttribute("list", list);

        return "common/mainPage";
	}
	
	
	
	
	
	
}
