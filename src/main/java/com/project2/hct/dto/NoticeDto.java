package com.project2.hct.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class NoticeDto {
	
	private String nbNo;
	private String nbTitle;
	private String nbContent;
	private Timestamp nbDate;	

}
