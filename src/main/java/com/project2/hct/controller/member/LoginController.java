package com.project2.hct.controller.member;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project2.hct.entity.Member;
import com.project2.hct.entity.Noticeboard;
import com.project2.hct.repository.MemberRepository;
import com.project2.hct.service.LiveroomService;
import com.project2.hct.service.NoticeboardService;

@Controller
public class LoginController {
	
	@Autowired
	private NoticeboardService noticeboardservice;
	@Autowired
	private LiveroomService liveroomservice;
	@Autowired
	MemberRepository memberRepository;

	@GetMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@GetMapping(value = {"/","/main"})
	public String main(Model model) {
		
		String id = SecurityContextHolder.getContext().getAuthentication().getName();	//아이디값
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		
		String role = auth.getAuthority();		//role값
		
		model.addAttribute("memId", id);
		model.addAttribute("role", role);
		model.addAttribute("livelist",liveroomservice.getList());
		
		return "main";
	}
	
	@GetMapping("/MainboardList")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> noticemain() {
		List<Noticeboard> nblist = noticeboardservice.getMainList();
		Map map = new HashMap<>();
		map.put("nblist", nblist);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// ID 찾기 페이지
	@GetMapping("/id_find")
	public String idFind() {
		return "member/idFindPage";
	}
	// PW 찾기 페이지
	@GetMapping("/pw_find")
	public String pwFind() {
		return "member/pwFindpage";
	}
	// 아이디 찾기
	@PostMapping("/find_id.do")
	public String findId(@RequestParam("memName") String memName, 
			@RequestParam("memTel") String memTel, Model model) {
		
		// 이름과 전화번호 일치하는지 조회
		Member member = memberRepository.checkname_tel(memName,memTel);
				
		// 일치하는 경우에만 아이디 리턴 		
		if(member !=null) {
			
			System.out.println(member.getMemId());
			
			model.addAttribute("memId",member.getMemId());
			
			return "member/idFindSucc";
		}
		else {
			return "member/idFindFail";
		}
	}
	
}
