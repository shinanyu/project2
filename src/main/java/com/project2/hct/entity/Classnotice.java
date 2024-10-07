package com.project2.hct.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "classnotice")
public class Classnotice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clnNo;
	private String clnContent;
	// FK ~ 수업 테이블의 PK 값 (ManyToOne, JoinColumn)
	private String clnClno;
	
	@CreationTimestamp
	private Timestamp clnDate;

}

