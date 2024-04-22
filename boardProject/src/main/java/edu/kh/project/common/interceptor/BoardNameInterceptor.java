package edu.kh.project.common.interceptor;

import java.util.List;
import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardNameInterceptor implements HandlerInterceptor {

	// 후처리 (Controller -> Dispatcher Servlet 사이)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {

		// application scope에서 boardTypeList 얻어오기
		ServletContext application = request.getServletContext();

		application.getAttribute("boardTypeList");

		// boardTypeList
		// List<Map> 형식
		// [{boardCode:1, boardName=공지}, {boardCode:2, boardName=자유}, ...]

		List<Map<String, Object>> boardTypeList = (List<Map<String, Object>>) application.getAttribute("boardTypeList");

		log.debug(boardTypeList.toString());

		// log.debug("boardCode : " + request.getAttribute("boardCode")); 안됨

		// Uniform Resource Identifier : 통합 자원 식별자 "/board/1" 
		// Uniform Resource Locater : 통합 자원 주소? "localhost/board/1" 
		// - 자원 이름만 봐도 무엇인지 구별할 수 있는 문자열
		String uri = request.getRequestURI();
		
		// log.debug("uri : " + uri);
				
		try {
		
		int boardCode = Integer.parseInt( uri.split("/")[2] ); // ["", "board", "1"]
		
		// boardTypeList에서 boardCode를 하나씩 꺼내어 비교
		for(Map<String, Object> boardType : boardTypeList) {

			// String.valueOf(값) : String으로 변환
			int temp = 
			Integer.parseInt(String.valueOf( boardType.get("boardCode") ));
			
			// 비교 결과가 같다면
			// request scope에 boardName을 추가
			if(temp == boardCode) {
				request.setAttribute("boardName", boardType.get("boardName"));
				break;
			}
		}
		
		} catch (Exception e) {
			
		}

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
