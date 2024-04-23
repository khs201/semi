package edu.kh.ib.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.board.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper mapper;
	
	
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		
		return mapper.selectBoardTypeList();
	}

}
