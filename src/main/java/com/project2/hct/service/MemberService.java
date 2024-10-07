package com.project2.hct.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project2.hct.entity.Member;
import com.project2.hct.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public Optional<Member> getMember(String id) {
		return memberRepository.findById(id);
	}

	// 페이징처리까지 리스트 select
	// (PageRequest = pageable 인터페이스의 구현체. 매개변수, n개씩 페이징처리, 데이터 정렬 방법과 기준)
	public Page<Member> list(int page){
		return memberRepository.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "memId")));
	}
	// 회원삭제
	public void deleteMember(String id) {
		
		Optional<Member> opMem = memberRepository.findById(id);
		
		if(opMem.isPresent()) {
			Member member = opMem.get();
			// 업데이트할 필드만 설정
			member.setMemActive("n");
			//update
			memberRepository.save(member);
		}else {
		}
	}
	// 닉네임 가져오기
	public String getNick(String id) {
		return memberRepository.findBymemId(id).getMemNickname();
	}
	// 비번찾기에서 ID랑 이름 확인
	public Member checkid_name(String memId, String memName) {
		return memberRepository.checkid_name(memId, memName);
	}
	// 회원찾기
	public Member searchUser(String nick) {
		return memberRepository.findBymemNickname(nick);
	}
	// 강사 회원 리스트
	// (PageRequest = pageable 인터페이스의 구현체. 매개변수, n개씩 페이징처리, 데이터 정렬 방법과 기준)
	public Page<Member> searchlist(int page) {
		return memberRepository.findByMemRole("ROLE_BUSINESS",PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "memId")));
	}
}
