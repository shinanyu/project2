package com.project2.hct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.hct.dto.ClassnoticeDTO;
import com.project2.hct.service.ClassnoticeService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;



@Controller
public class ClassnoticeController {

	@Autowired
	private ClassnoticeService cn;
	
	@RequestMapping(value="classnotice/write.do")
	public String write(HttpServletRequest hsr, Model model) {
		String clnContent = hsr.getParameter("clnContent");
		String clnClno = hsr.getParameter("clnClno");
		
		ClassnoticeDTO classnoticeDTO = new ClassnoticeDTO();
		classnoticeDTO.setClnClno(clnClno);
		classnoticeDTO.setClnContent(clnContent);
		cn.save(classnoticeDTO);
		model.addAttribute("clnClno", clnClno);
		return "classnotice/writeSuccess.html";
	}
	
	@RequestMapping(value="classnotice/delete/{clnClno}/{clnNo}")
	public String delete(@PathVariable("clnClno") String clnClno,
			@PathVariable("clnNo") Long clnNo, Model model) {
		cn.delete(clnNo);
		model.addAttribute("clnClno", clnClno);
		return "classnotice/deleteSuccess.html";
	}
}
