package com.project2.hct.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project2.hct.entity.Member;

//로그인 검증 로직 클래스 (dto)
//CustomUserDetailService로부터 받은 회원정보를 SecurityConfig클래스에 전달해줌
//SecurityConfig클래스에서 해당 회원에 대한 password, role등을 검증 후
//session에 저장해서 해당 사용자 접근 시 허용해줌

//로그인 진행 완료 시
//시큐리티 session을 만들어줌 
//Security Session ( Authentication타입(UserDetails타입) )

public class CustomUserDetails implements UserDetails{
	
	private Member member;
	
	public CustomUserDetails(Member member) {
		this.member = member;
	}
	
	// 사용자의 특정 권한에 대해 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
		collection.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return member.getMemRole();	//권한 리턴
			}
		});
		
		return collection;
	}

	// 비밀번호 리턴
	@Override
	public String getPassword() {
		return member.getMemPw();
	}

	// 회원이름 리턴
	@Override
	public String getUsername() {
		return member.getMemId();
	}

	// 권한이 만료되었는지 > 우리 프로젝트에서는 따로 기획하지 않음 > 강제로 만료되지 않음(true)으로 설정
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 이하동문
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return member.getMemActive().equals("y");
	}

}
