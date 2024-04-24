package edu.kh.ib.board.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.board.model.mapper.EditBoardMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class EditBoardServiceImpl implements EditBoardService{
	
	private final EditBoardMapper mapper;
	
	
	
	

}
