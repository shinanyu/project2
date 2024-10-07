package com.project2.hct.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import com.project2.hct.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// OAuth2 관련
	private final CustomOAuth2UserService customOAuth2UserService;
	
	public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
		this.customOAuth2UserService = customOAuth2UserService;
	}
	
	//비밀번호 암호화
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

	//SecurityFilterChain 이라는 인터페이스를 리턴타입으로 지정
	//내부에는 특정 요청에 대한 처리 해줌 (인가)
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{	
		
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		requestCache.setMatchingRequestParameterName(null);
		
		http
			.requestCache(request-> request
				.requestCache(requestCache))
			.authorizeHttpRequests((auth)-> auth
					.requestMatchers("/","/main","/loginForm","/login","/joinForm","/n_joinForm","/b_joinForm","/b_join","/n_join","/idcheckB",
							"/MainboardList", "/healthclass/**", "/chat/**", "/ws/**").permitAll()
					.requestMatchers("/id_find","find_id.do", "/pw_find","find_pw.do", "/noticelist/**", "/qnaboard/**", "/qnareply/**").permitAll()
					.requestMatchers("/css/**", "/image/**", "/js/**", "favicon.io","/upload/image/**").permitAll()
					.requestMatchers("/stomp/**", "/pub/**", "/sub/**").permitAll()
					.requestMatchers("/admin","/noticelist/admin/**").hasRole("ADMIN")
					.requestMatchers("/myClassP").hasRole("BUSINESS")
					.requestMatchers("/oauth2/**").permitAll()
					.anyRequest().authenticated()
		);
		
		//권한 필요한 페이지 접속 시 로그인 창이 뜨도록
		http
			.formLogin((auth)->auth.loginPage("/loginForm")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/main",true)
					.usernameParameter("memId")
					.passwordParameter("memPw")
		);
		
		// ROLE 접근 제한 처리
		http.exceptionHandling((except) -> except
				.accessDeniedPage("/accessDenied"));
		
		// 로그아웃
		http
			.logout((auth)-> auth.logoutUrl("/logout")
						.logoutSuccessUrl("/main"));
		
//		csrf: 위조 방지용 > 원래 login 시 csrf 토큰도 보내주어야 로그인이 정상적으로 진행이 됨 > 개발단계에서는 일단 이 설정을 off해둠
		http
			.csrf((auth)-> auth.disable());
		
		// 다중 로그인 관련 처리
		http	
			.sessionManagement((auth) -> auth
			.maximumSessions(2)						//하나의 아이디에서 중복 로그인 가능하도록 하는 갯수
			.maxSessionsPreventsLogin(true));		//위에서 설정한 갯수 초과할 경우 처리 > true:새로운 로그인 차단
		
		// 세션 고정 공격 보호
		http
			.sessionManagement((auth)-> auth
					.sessionFixation().changeSessionId());		//로그인 시 동일한 세션에 대한 id변경
		
		// .oauth2Login(): 소셜 로그인에 필요한 내부
		http
			.oauth2Login((oauth2)->oauth2
					.loginPage("/loginForm")
					.defaultSuccessUrl("/main", true)
					.userInfoEndpoint((UserInfoEndpointConfig)->							
							UserInfoEndpointConfig.userService(customOAuth2UserService)));	
		
		//userInfoEndpoint는 데이터를 받을 수 있는 dto객체를 의미(?)
		//dto객체에 대한 서비스로 클래스로 만든 customOAuth2UserService를 등록
		
		return http.build();
	}
	
}
