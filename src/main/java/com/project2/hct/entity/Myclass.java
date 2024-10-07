package com.project2.hct.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "myclass")
public class Myclass {
	// 수강중인 수업목록
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mycNo;
	private String mycMemid;
	// FK ~ 수업 테이블의 PK 값 (ManyToOne, JoinColumn)
	private String mycClno;
	private String mycClname;
	private String mycClmemname;

}

