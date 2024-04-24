package edu.kh.ib.board.interceptor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.ib.board.model.service.BoardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class BoardTypeInterceptor implements HandlerInterceptor{
	
	@Autowired
	private BoardService service;
	
	// 게시판 목록 얻어와서 HTML application scope에 뿌리기
	// 전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
				
				// application scope 객체 얻어오기
				ServletContext application = request.getServletContext();
				
				// application scope에 boardTypeList가 없을 경우
				if(application.getAttribute("boardTypeList") == null) {
					
					log.info("BoardTypeInterceptor - postHandle(전처리) 동작 실행");
					
					// boardTypeList 조회 서비스 호출
					List<Map<String, Object>> boardTypeList = service.selectBoardTypeList();   
					
					// 조회 결과를 application scope에 추가
					application.setAttribute("boardTypeList", boardTypeList);
					
				}
				
				return HandlerInterceptor.super.preHandle(request, response, handler);
		
	}
	
	// 후처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	// 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	

}
