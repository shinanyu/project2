package com.project2.hct.dto;

import lombok.Data;

@Data
public class JoinDTO {

	private String memId;
	private String memPw;
	private String memNickname;
	private String memName;
	private String memBirth;
	private String memTel;
	private String memRole;
	private String memActive;
	
	private String certMemid;
	private String certName;
	private String certReg;	
	private String certDepart;
	
	private String memChangePw;
	
}
