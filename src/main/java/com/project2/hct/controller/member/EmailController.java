package com.project2.hct.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project2.hct.entity.Member;
import com.project2.hct.service.EmailService;
import com.project2.hct.service.MemberService;


@Controller
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/find_pw.do")
	public String send_email(@RequestParam("memId") String memId,
			@RequestParam("memName") String memName) {
		
		//해당 회원이 존재하는지 조회
		Member member = memberService.checkid_name(memId, memName);
		
		//회원이 존재하는 경우 메일 보냄 (회원이 존재하지 않는 경우는 아직 따로 구현을 안했음)
		if(member !=null) {
			emailService.sendSimpleEmail(memId);
			return "member/pwFindSucc";
		}
		else {
			return "member/pwFindFail";
		}
	}
	
}
