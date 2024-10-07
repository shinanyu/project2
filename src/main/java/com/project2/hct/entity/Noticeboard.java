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
@Table(name= "noticeboard")
public class Noticeboard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nbNo;
	private String nbTitle;
	private String nbContent;
	
	@CreationTimestamp
	private Timestamp nbDate;

}

