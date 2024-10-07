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
@Table(name= "qnareply")
public class Qnareply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qnarNo;
	private String qnarOrigin;
	private String qnarContent;
	private String qnarWriter;
	private String qnarNickname;
	@CreationTimestamp
	private Timestamp qnarDate;

}

