package com.project2.hct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.entity.Member;
import com.project2.hct.repository.MemberRepository;

@Service
public class IdcheckService {

	@Autowired
	MemberRepository memberRepository;
	
	public Member idcheck(String id) {
		Member member = memberRepository.findBymemId(id);
		
		return member;
	}
}
