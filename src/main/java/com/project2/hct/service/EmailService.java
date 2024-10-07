package com.project2.hct.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project2.hct.entity.Member;
import com.project2.hct.repository.MemberRepository;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void sendSimpleEmail(String memId) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		//임시 비밀번호 생성
		Random random = new Random();
		String send_password = String.valueOf(random.nextInt(9999));
		
//      message.setFrom("");	//따로 설정하지 않으면 > yml파일의 username설정대로
        message.setTo(memId);
        message.setSubject("[Online Health-Care] 임시 비밀번호 입니다.");	//메일 제목
        message.setText("유저의 임시 비밀번호는" +send_password+ "입니다. 로그인 후 반드시 비밀번호를 변경해주세요.");
        
        // 업데이트 할 멤버 찾기
 		Optional<Member> opMem = memberRepository.findById(memId);
 		
 		if(opMem.isPresent()) {
 			Member member = opMem.get();
 			// 업데이트할 필드만 설정
 			member.setMemPw(bCryptPasswordEncoder.encode(send_password));
 			//update
 			memberRepository.save(member);
 		}else {
 		}
        
        mailSender.send(message);
	}
	
}

