package com.project2.hct.service;

import java.util.Random;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project2.hct.dto.CustomOAuth2User;
import com.project2.hct.dto.GoogleResponse;
import com.project2.hct.dto.NaverResponse;
import com.project2.hct.dto.OAuth2Response;
import com.project2.hct.entity.Member;
import com.project2.hct.repository.MemberRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService{
	
	private final MemberRepository memberRepoisotory;
	public CustomOAuth2UserService(MemberRepository memberRepoisotory) {
		this.memberRepoisotory = memberRepoisotory;
	}
	
	// 매개변수 userRequest에 naver, google등의 인증 데이터 넘어옴
	@Override									
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		
		// super(부모)의 loadUser메서드 통해 인증자 데이터 가져옴
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		OAuth2Response oAuth2Response = null;
		
		// registraionId = naver인지 google인지
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		if(registrationId.equals("naver")) {		//naver로그인
			oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
		}
		else if(registrationId.equals("google")){	//google로그인
			oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
		}
		else{	
			return null;
		}
		
		// username = naver(또는 google) + naver(또는 google)에서 임의로 준 id (사용자name이랑 다름 - 주의!!)
		String username = oAuth2Response.getProvider()+""+oAuth2Response.getProviderId();
		
		// 해당 유저가 naver멤버 테이블에 존재하는지 조회
		String email = oAuth2Response.getEmail();
		Member existData = memberRepoisotory.findBymemId(email);
		
		String role = null;
		
		if(existData == null) {		//새로운 로그인 > db에 정보 저장
			Member member = new Member();
			
			member.setSocial(oAuth2Response.getProvider());
			member.setMemName(username);
			member.setMemId(oAuth2Response.getEmail());
			
			Random r = new Random();
			int random=0;
			String nickname=null;
			Member member1=new Member();
			
			do {
			    random = r.nextInt(9999);
			    nickname = "사용자" + random;
			    
			    // 중복 확인
			    member1 = memberRepoisotory.findBymemNickname(nickname);
			    
			} while (member1 != null);
			
			member.setMemNickname(nickname);
			member.setMemTel(oAuth2Response.getMobile());
			member.setMemRole("ROLE_NORMAL");
			member.setMemActive("y");
			
			memberRepoisotory.save(member);
		}
		else {
			//새로운 정보 update
			role = existData.getMemRole();
			memberRepoisotory.save(existData);
		}
		
		//나머지 구현
		return new CustomOAuth2User(oAuth2Response, role);
	}
	
}
