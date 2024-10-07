package com.project2.hct.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
//resource서버(naver, google..)로부터 넘어오는 모든 데이터를 받음
public class CustomOAuth2User implements OAuth2User{

	private final OAuth2Response oAuth2Response;
	private final String role;
	
	public CustomOAuth2User(OAuth2Response oAuth2Response, String role) {
		this.oAuth2Response = oAuth2Response;
		this.role = role;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return role;
			}
		});
		return collection;
	}

	@Override
	public String getName() {
		return oAuth2Response.getName();
	}
	
	//강제로 id값 리턴해주는 메서드 만듦
	public String getUsername() {
		return oAuth2Response.getProvider()+""+oAuth2Response.getProviderId();
	}
}
