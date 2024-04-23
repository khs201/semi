package edu.kh.ib.login.controller;

import org.springframework.stereotype.Controller;

import edu.kh.ib.login.model.serivce.loginService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class loginController {

	private final loginService serivce;
	
		
}
