package com.project2.hct.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project2.hct.dto.JoinDTO;
import com.project2.hct.service.JoinService;

@Controller
public class Join_Controller {

	@Autowired
	private JoinService joinService;
	
	@GetMapping("joinForm")
	public String register() {
	
		return "member/join01";
	}
	
	// 일반(normal) 회원
	@GetMapping("n_joinForm")
	public String n_joinForm() {
	
		return "member/n_joinForm";
	}
	
	// 일반 회원 회원가입
	@PostMapping("/n_join")
	public String n_join(JoinDTO joinDto) {
		
		joinService.n_join(joinDto);
		
		//회원가입 완료 후 바로 메인으로
		return "redirect:/main";
	}
	
	// 비즈니스(=business) 회원
	@GetMapping("b_joinForm")
	public String b_joinForm() {
		
		return "member/b_joinForm";
	}
	// 비즈니스 회원 회원가입
	@PostMapping("/b_join")
	public String b_join(JoinDTO joinDto) {
		
		joinService.b_join(joinDto);	
		
		//회원가입 완료 후 바로 메인으로
		return "redirect:/main";
	}
	
	// 아이디 중복검사
	@PostMapping("/idcheckB")
	@ResponseBody
	public int idcheck(@RequestParam("id") String id) {
		int cnt = joinService.idCheck(id);
		return cnt;
	}

}
