package edu.kh.ib.login.model.serivce;

import org.springframework.stereotype.Service;

import edu.kh.ib.login.model.mapper.loginMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class loginServiceImpl implements loginService {

	private final loginMapper mapper;
}
