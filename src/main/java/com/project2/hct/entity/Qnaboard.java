package com.project2.hct.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "qnaboard")
public class Qnaboard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qnaNo;
	private String qnaTitle;
	private String qnaContent;
	private String qnaWriter;
	private String qnaNickname;
	private String isprivate;
	@CreationTimestamp
	@Column(name = "qna_date", nullable=false, updatable = false)
	private Timestamp qnaDate;

}

