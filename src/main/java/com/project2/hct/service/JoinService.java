package com.project2.hct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project2.hct.dto.JoinDTO;
import com.project2.hct.entity.Certificate;
import com.project2.hct.entity.Member;
import com.project2.hct.repository.CertificateRepositorty;
import com.project2.hct.repository.MemberRepository;

@Service
public class JoinService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private CertificateRepositorty cerRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void b_join(JoinDTO joinDto) {
		
		//db에 이미 동일한 id를 가진 회원이 존재하는지 검증 후 회원가입
		boolean isUser = memberRepository.existsBymemId(joinDto.getMemId());
		//true면(=id존재하면) 강제로 함수 리턴시킴
		if(isUser) {return;}
	
		//회원가입 > member 테이블
		Member member = new Member();
		
		member.setMemId(joinDto.getMemId());
		member.setMemPw(bCryptPasswordEncoder.encode(joinDto.getMemPw()));
		member.setMemNickname(joinDto.getMemNickname());
		member.setMemName(joinDto.getMemName());
		member.setMemBirth(joinDto.getMemBirth());
		member.setMemTel(joinDto.getMemTel());
		member.setMemActive("y");
		member.setSocial("not");
		if(joinDto.getMemRole() == null) {
			member.setMemRole("ROLE_BUSINESS");
		}
		
		//회원정보 insert
		memberRepository.save(member);
		
		
		//자격증 정보 > certificate 테이블
		Certificate certificate = new Certificate();
		
		certificate.setCertMemid(joinDto.getMemId());
		certificate.setCertName(joinDto.getCertName());
		certificate.setCertReg(joinDto.getCertReg());
		certificate.setCertDepart(joinDto.getCertDepart());

		//자격증 정보 insert
		cerRepository.save(certificate);
		
	}
	
	public void n_join(JoinDTO joinDto) {
		
		//db에 이미 동일한 id를 가진 회원이 존재하는지 검증 후 회원가입
		boolean isUser = memberRepository.existsBymemId(joinDto.getMemId());
		//true면(=id존재하면) 강제로 함수 리턴시킴
		if(isUser) {return;}
		
		//그게 아니면 > 회원가입
		Member member = new Member();
		
		member.setMemId(joinDto.getMemId());
		member.setMemPw(bCryptPasswordEncoder.encode(joinDto.getMemPw()));
		member.setMemNickname(joinDto.getMemNickname());
		member.setMemName(joinDto.getMemName());
		member.setMemBirth(joinDto.getMemBirth());
		member.setMemTel(joinDto.getMemTel());
		member.setMemActive("y");
		member.setSocial("not");
		if(joinDto.getMemRole() == null) {
			member.setMemRole("ROLE_NORMAL");
		}
		
		//insert
		memberRepository.save(member);
	}

	public int idCheck(String id) {
		int cnt = 0;
		//db에 이미 동일한 id를 가진 회원이 존재하는지 검증
		boolean isUser = memberRepository.existsBymemId(id);
		if(isUser) { // true면(=id 중복) int 1 리턴
			cnt = 1;
			return cnt;
		}else { // false면(=id 중복x) int 0 리턴
			return cnt;
		}
		
	}
}
