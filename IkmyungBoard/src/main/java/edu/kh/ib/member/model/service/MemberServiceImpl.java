package edu.kh.ib.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.member.model.mapper.MemberMapper;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

	@Autowired
	
	private MemberMapper mapper;
}
