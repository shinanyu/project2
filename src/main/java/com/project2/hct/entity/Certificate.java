package com.project2.hct.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "certificate")
@DynamicUpdate
public class Certificate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long certNo;
	// FK ~ 멤버 테이블의 PK 값 -강사ID (ManyToOne, JoinColumn)
	private String certMemid;
	private String certName;
	private String certReg;
	private String certDepart;
	
}

