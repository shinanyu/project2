package com.project2.hct.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.hct.dto.ChatRoomDTO;
import com.project2.hct.dto.LiveroomDTO;
import com.project2.hct.service.ChatRoomService;
import com.project2.hct.service.HealthclassService;
import com.project2.hct.service.LiveroomService;
import com.project2.hct.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LiveroomController {
	
	@Autowired
	private LiveroomService liveroomservice;
	@Autowired
	private ChatRoomService crs;
	@Autowired
	private MemberService memberService;
	@Autowired
	private HealthclassService healthclassService;
	
	private ChatRoomDTO createRoom(String roomId, String roomName) {
    	return crs.createChatRoomDTO(roomId,roomName);
    }
	
	// 강사 시작
	@RequestMapping("/videoroomtest")
	public String videoroomtest(Authentication auth, LiveroomDTO liveroomdto,HttpServletRequest hsr, Model model) throws IOException{
		if ( !liveroomservice.existsByLiveClno(liveroomdto.getLiveClno()) )
			liveroomservice.save(liveroomdto);
		
		String id = auth.getName();
		String nick = memberService.getNick(id);
		String liveClno = hsr.getParameter("liveClno");
		
		if ( crs.findByRoomId(liveClno) == null ) {
    		createRoom(liveClno, hsr.getParameter("liveName"));
    	}
    	ChatRoomDTO room = crs.findByRoomId(liveClno);
    	
    	String Checkid = healthclassService.findByClNo(liveClno).getClMemid();
		
		// 해당 강의 강사인지 확인
		if(id.equals(Checkid)) {
			String owner = "y";
			model.addAttribute("owner", owner);
		}else {
			String owner = "n";
			model.addAttribute("owner", owner);
		}

    	model.addAttribute("liveclno", liveClno);
        model.addAttribute("room", room);
        model.addAttribute("username", hsr.getParameter("username"));
        model.addAttribute("roomName", room.getRoomName());
		
		model.addAttribute("nickname", nick);
		return "videoroomtest";
	}
	// 수강생 시작
	@RequestMapping("/videoroomsstu")
	public String videoroomstu(Authentication auth, Model model, HttpServletRequest hsr) {
		String liveClno = hsr.getParameter("liveClno");
		String id = auth.getName();
		String nick = memberService.getNick(id);
		
		if ( crs.findByRoomId(liveClno) == null ) {
    		createRoom(liveClno, hsr.getParameter("liveName"));
    	}
    	ChatRoomDTO room = crs.findByRoomId(liveClno);
    	
		
		String Checkid = healthclassService.findByClNo(liveClno).getClMemid();
		
		// 해당 강의 강사인지 확인
		if(id.equals(Checkid)) {
			String owner = "y";
			model.addAttribute("owner", owner);
		}else {
			String owner = "n";
			model.addAttribute("owner", owner);
		}

		model.addAttribute("liveclno", liveClno);
        model.addAttribute("room", room);
        model.addAttribute("username", hsr.getParameter("username"));
        model.addAttribute("roomName", room.getRoomName());
		model.addAttribute("nickname", nick);
		return "videoroomtest";
	}
	// 강사 끝내기
	@RequestMapping("/delete/{liveclno}")
	public String delete(@PathVariable("liveclno") String live_clno) throws IOException {
		live_clno += "(";
		int idx = live_clno.indexOf("(");
		live_clno = live_clno.substring(0,idx);
		liveroomservice.delete(live_clno);
		return "liveroom";
	}
}