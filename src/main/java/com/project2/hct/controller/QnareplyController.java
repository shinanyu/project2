package com.project2.hct.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.hct.dto.QnareplyDTO;
import com.project2.hct.service.MemberService;
import com.project2.hct.service.QnaboardService;
import com.project2.hct.service.QnareplyService;

@Controller
public class QnareplyController {

	@Autowired
	private QnareplyService qrs;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="qnareply/write.do")
	public String write(QnareplyDTO qnar, Model model) {
		// 넘어오는 ID값으로 닉네임 추가
		qnar.setQnarNickname(memberService.getNick(qnar.getQnarWriter()));
		qrs.save(qnar);
		model.addAttribute("qnarOrigin", qnar.getQnarOrigin());
	
		return "qnareply/writeSuccess.html";
	}
	
	@RequestMapping(value="qnareply/delete/{qnarOrigin}/{qnarNo}")
	public String delete(@PathVariable("qnarOrigin") String qnarOrigin,
			@PathVariable("qnarNo") Long qnarNo, Model model,Authentication auth) {
		
		String id = auth.getName();
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority authority = iter.next();
		
		String role = authority.getAuthority();
		
		if(id.equals(qrs.findByQnarNo(qnarNo).getQnarWriter()) || role.equals("ROLE_ADMIN")) {
			
		}else {
			return "qnareply/deleteFail.html";
		}
		qrs.deleteByQnarNo(qnarNo);
		model.addAttribute("qnarOrigin", qnarOrigin);
		
		return "qnareply/deleteSuccess.html";
	}
}