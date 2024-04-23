package edu.kh.ib.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.board.model.dto.Pagination;
import edu.kh.ib.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper mapper;
	
	// 게시판 목록 조회해오기
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		
		return mapper.selectBoardTypeList();
	}

	// 
	@Override
	public String getBoardName(int boardCode) {

		
		
		return mapper.getBoardName(boardCode);
	}
	
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		
		// 삭제되지 않은 게시글 수 조회
		int listCount = mapper.getListCount(boardCode);
		
		// 게시글 수를 바탕으로 페이지 네이션 객체 생성
		edu.kh.ib.board.model.dto.Pagination pagination = new Pagination(cp, listCount);
		
		// 특정 게시판의 지정된 페이지 목록 조회
		
		/* ROWBOUNDS 객체 (Mybatis 제공 객체)
		 * - 지정된 크기(offset)만큼 건너뛰고
		 *   제한된 크기(limit)만큼의 행을 조회하는 객체
		 *   
		 *    --> 페이징 처리가 굉장히 간단해짐!!
		 *  */
		
		int limit = pagination.getLimit();
		
		int offset = (cp - 1) * limit; 
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		/* Mapper 메서드 호출 시
		 * - 첫 번째 매개 변수 -> SQL에 전달할 파라미터
		 * - 두 번째 매개 변수 -> RowBounds 객체 전달 변수
		 *  */
		List<edu.kh.ib.board.model.dto.Board> boardList = mapper.selectBoardList(boardCode, rowBounds);
		
		
		// 4. 목록 조회 결과 + Pagination 객체를 Map으로 묶음
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		
		
		
		// 5. 결과 반한
		
		
		return map;
		
	}
	
	
	
	
}
