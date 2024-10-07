package com.project2.hct.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project2.hct.dto.QnaboardDTO;
import com.project2.hct.entity.Qnaboard;
import com.project2.hct.service.MemberService;
import com.project2.hct.service.QnaboardService;
import com.project2.hct.service.QnareplyService;

@Controller
public class QnaboardController {
	
	@Autowired
	private QnaboardService qs;
	@Autowired
	private QnareplyService qrs;
	@Autowired
	private MemberService ms;
	
	@RequestMapping("qnaboard")
	public String f1(Model model, @RequestParam(required=false,defaultValue="0",value="page") int page) {
		Page<Qnaboard> listPage = qs.list(page);
		int totalPage = listPage.getTotalPages();
		
		List<Qnaboard> qnaList = listPage.getContent();
		
		if(qnaList.isEmpty()) {
			model.addAttribute("message", "등록된 글이 없습니다.");
		}else {
			model.addAttribute("qnaList", qnaList);
		}
		
		model.addAttribute("totalPage", totalPage);
		return "/qnaboard/list";
	}
	@RequestMapping("qnaboard/writeform")
	public String writeform() {
		return "qnaboard/writeform.html";
	}
	
	@RequestMapping("qnaboard/write")
	public String write(QnaboardDTO qna,Authentication auth) {
		if ( qna.getIsprivate() == null)
			qna.setIsprivate("off");
		String id = auth.getName();
		String nick = ms.getNick(id);
		qna.setQnaNickname(nick);
		qna.setQnaWriter(id);
		qs.save(qna);
		
		return "/qnaboard/writeSuccess";
	}
	
	@RequestMapping("qnaboard/delete/{qnaNo}")
	public String delete(@PathVariable("qnaNo") Long qnaNo) {
		qs.delete(qnaNo);
		return "qnaboard/deleteSuccess.html";
	}
	
	@RequestMapping("qnaboard/updateform/{qnaNo}")
	public String updateform(@PathVariable("qnaNo") Long qnaNo, Model model) {
		model.addAttribute("qna", qs.findByQnaNo(qnaNo));
		return "qnaboard/updateform.html";
	}
	
	@RequestMapping("qnaboard/update")
	public String update(QnaboardDTO qna,Authentication auth) {
		if ( qna.getIsprivate() == null)
			qna.setIsprivate("off");
		String id = auth.getName();
		String nick = ms.getNick(id);
		qna.setQnaNickname(nick);
		qna.setQnaWriter(id);
		qs.save(qna);
		return "/qnaboard/updateSuccess";
	}
	@RequestMapping("qnaboard/detail/{qnaNo}")
	public String detail(@PathVariable("qnaNo") Long qnaNo, Model model,Authentication auth) {
		// 비회원도 볼수있게
		if(auth == null) {
			
		}else {
			// 해당 글의 작성자인지 확인
			String Checkid = qs.findByQnaNo(qnaNo).getQnaWriter();
			
			if(auth.getName().equals(Checkid)) {
				String owner = "y";
				model.addAttribute("owner", owner);
			}else {
				String owner = "n";
				model.addAttribute("owner", owner);
			}
			// 권한확인
			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			Iterator<? extends GrantedAuthority> iter = authorities.iterator();
			GrantedAuthority authority = iter.next();
			
			String role = authority.getAuthority();
			
			model.addAttribute("role", role);
		}
		QnaboardDTO qna = qs.findByQnaNo(qnaNo);
		
		model.addAttribute("qnaNo", qnaNo);
		model.addAttribute("qna", qna);
		model.addAttribute("qnareplylist", qrs.findByQnarOrigin(qnaNo.toString()));
		return "qnaboard/detail.html";
	}
}
