package com.project2.hct.dto;

public interface OAuth2Response {

	//제공자 (naver, google ...)
	String getProvider();
	
	//제공자에서 발급해주는 아이디(번호)
	String getProviderId();
	
	String getEmail();
	
	String getName();
	
	String getNickname();
	
	String getBirthday();
	
	String getBirthyear();
	
	String getMobile();
	
}
