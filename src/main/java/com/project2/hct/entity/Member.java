package com.project2.hct.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "member")
@DynamicUpdate
public class Member {
	
	@Id
	private String memId;
	private String memPw;
	private String memNickname;
	private String memName;
	private String memBirth;
	private String memTel;
	private String memRole;
	private String memActive;
	private String social;
	
	@CreationTimestamp  // 생성시 당일날짜 자동 입력 - 가입일
	private Timestamp memReg;
	
	@UpdateTimestamp  // 업데이트시 당일날짜 자동 입력 - 탈퇴일
	private Timestamp memDel;
}

