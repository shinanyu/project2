package com.project2.hct.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project2.hct.entity.Certificate;
import com.project2.hct.entity.Member;
import com.project2.hct.service.CertificateService;
import com.project2.hct.service.MemberService;

@Controller
public class AdminController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private CertificateService certificateSv;

	// 회원 리스트 출력
	@GetMapping("/admin")
	public String getUserList(Model model, @RequestParam(required=false,defaultValue="0",value="page") int page){
		Page<Member> listPage = memberService.list(page); // 불러올 페이지 데이터 1페이지는 0부터 시작
		int totalPage = listPage.getTotalPages(); // 총 페이지 수
		
		model.addAttribute("member", listPage.getContent());
		model.addAttribute("totalPage", totalPage);
		
		return "mypage/admin";
	}

	// 회원 탈퇴시키기
	@PostMapping("/deleteUser")
	public String deleteUser(@RequestParam("memId") String memId) {
		
		memberService.deleteMember(memId);
		
		return "mypage/admin";
	}
	
	// 회원 검색
	@GetMapping("/admin/search")
	public String searchUser(@RequestParam("searchKeyword") String nick,Model model) {
		
		Member mem = memberService.searchUser(nick);
		model.addAttribute("mem", mem);
		
		return "mypage/adminSearch";
	}
	
	// 강사회원리스트
	@GetMapping("/admin/adminBlist")
	public String adminBlist(Model model, @RequestParam(required=false,defaultValue="0",value="page") int page) {
		
		Page<Member> listPage = memberService.searchlist(page); // 불러올 페이지 데이터 1페이지는 0부터 시작
		int totalPage = listPage.getTotalPages(); // 총 페이지 수
		
		model.addAttribute("member", listPage.getContent());
		model.addAttribute("totalPage", totalPage);
		
		return "mypage/adminBlist";
	}
	
	// 자격증 확인 페이지
	@PostMapping("/admin/checkCertificate")
	public String checkCert(Model model,@RequestParam("memId") String memId) {
		System.out.println(memId);
		List<Certificate> list = certificateSv.getCert(memId);
		model.addAttribute("list", list);
		
		return "mypage/checkCert";
	}
}
