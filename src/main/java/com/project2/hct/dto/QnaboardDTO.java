package com.project2.hct.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QnaboardDTO {

	private Long qnaNo;
	private String qnaTitle;
	private String qnaContent;
	private String qnaWriter;
	private String qnaNickname;
	private String isprivate;
	private Timestamp qnaDate;
}
