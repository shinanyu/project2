package com.project2.hct.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QnareplyDTO {

	private Long qnarNo;
	private String qnarOrigin;
	private String qnarContent;
	private String qnarWriter;
	private String qnarNickname;
	private Timestamp qnarDate;

}
