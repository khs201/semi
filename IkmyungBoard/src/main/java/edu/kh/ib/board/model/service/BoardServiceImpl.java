package edu.kh.ib.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.board.model.dto.Board;
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
	
	
	
	
	
	
	@Override
	public Board selectOne(Map<String, Integer> map) {
		// [여러 SQL을 실행하는 방법]
				// 1. 하나의 Service 메서드에서
				// 	  여러 Mapper 메서드를 호출
				
				// 2. 수행하려는 SQL이 
				// 1) 모두 SELECT 이면서
				// 2) 먼저 조회된 결과중 일부를 이용해
				// 	  나중에 수행되는 SQL의 조건으로 삼을 때
				
				// -> MyBatis의 <resultMap>, <collection> 태그
				// 	  Mapper 메서드 1회 호출로 여러 SELECT 한 번에 수행 가능
		
		
		return mapper.selectOne(map);
	}
	
	// 게시글 검색
	@Override
	public Map<String, Object> searchBoard(Map<String, Object> paramMap, int cp) {
		
		// paramMap에 포함된 값 : (key, query, boardCode)
		
		// 1. 지정된 게시판(boardCode)에서
		//    검색 조건에 맞으면서
		//    삭제되지 않은 게시글 수를 조회
		
		
		int listCount = mapper.getSearchCount(paramMap);
		
		// 2. 1번의 결과 + cp를 이용해서
		//    Pagination 객체를 생성
		// * Pagination 객체 : 게시글 목록 구성에 필요한 값을 저장한 객체
		
		Pagination pagination = new Pagination(cp, listCount);
		// 3. 지정된 페이지의 검색 결과 목록 조회
		int limit = pagination.getLimit(); 
		int offset = (cp - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit); 
		
		/* Mapper 메서드 호출 시
		 * - 첫 번째 매개 변수 -> SQL에 전달할 파라미터
		 * - 두 번째 매개 변수 -> RowBounds 객체 전달 
		 * */
		List<Board> boardList = mapper.selectSearchBoard(paramMap, rowBounds);
		
		// 4. 목록 조회 결과 + Pagination 객체를 Map으로 묶음
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
					
		// 5. 결과 반한
		return map;
		
	}
	
	/** 게시글 좋아요 체크/해제
	 * 
	 */
	@Override
	public int boardLike(Map<String, Integer> map) {


		int result = 0;
		
		// 1. 좋아요가 체크된 상태인 경우 (likeCheck == 1)
		// ->  BOARD_LIKE 테이블에 DELETE
		
		if(map.get("likeCheck") == 1) {
			result = mapper.deleteBoardLike(map);
		}
		
		// 2. 좋아요가 해제된 상태인 경우 (likeCheck == 0)
		// ->  BOARD_LIKE 테이블에 INSERT
		else {
			result = mapper.insertBoardLike(map);
		}
		
		
		// 3. 다시 해당 게시글의 좋아요 개수 조회해서 반환
		if(result > 0) {
			return mapper.selectLikeCount(map.get("boardNo"));
		}
		
		return -1;
	
		
		
	}
	
	@Override
	public int updateReadCount(int boardNo) {
		
		// 1. 조회 수 1 증가
		int result = mapper.updateReadCount(boardNo);
		
		// 2. 현재 조회 수 조회
		if(result > 0) {
			return mapper.selectReadCount(boardNo);
		}
		
		return -1;
		
		
	}
	
	
	
	/** 메인 페이지용 조회 서비스!!
	 *
	 */
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp, int limit) {
		
		// 삭제되지 않은 게시글 수 조회
		int listCount = mapper.getListCount(boardCode);
		
		 // limit을 파라미터로 받아 사용
		int offset = (cp - 1) * limit; 
		
		// RowBounds 객체를 사용하여 limit 수만큼만 조회
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		// MyBatis Mapper를 통해 특정 게시판의 지정된 페이지 목록 조회
	    List<Board> boardList = mapper.selectBoardList(boardCode, rowBounds);
	    
	    // 게시판 이름 가져오기
	    String boardName = getBoardName(boardCode);
	    
	    // 조회된 게시글 목록과 페이지네이션 정보를 Map으로 묶어 반환
	    Map<String, Object> map = new HashMap<>();
	    map.put("boardList", boardList);
	    map.put("boardName", boardName);

	    
	    
	    // 페이지 네이션은 조회하는 게시글 수가 제한적이므로 별도 객체를 생성하지 않음
	    return map;
		
		
	}
	
	
	
}
