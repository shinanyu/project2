package com.project2.hct.dto;

import lombok.Data;

@Data
public class HealthclassDTO {
	
	private Long clNo;
	// 수업종류 ( ~ : 1, ~ : 2 )
	private String clType;
	// 수업 이름
	private String clName;
	// FK ~ 멤버 테이블의 PK 값 -강사ID (ManyToOne, JoinColumn)
	private String clMemid;
	// 강사 이름
	private String clMemname;
	// 수업 소개
	private String clIntro;
	// 수업 시작 시간
	private String clStart;
	// 총 시간(분)
	private String clTime;
	// 수업 진행 요일
	private String clDay;
	// 썸네일용 사진
	private String clThumbnail;
	// 강사 프로필 사진
	private String clProfile;

}
