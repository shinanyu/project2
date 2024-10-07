package com.project2.hct.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.dto.QnareplyDTO;
import com.project2.hct.entity.Qnareply;
import com.project2.hct.repository.QnareplyRepository;

@Service
public class QnareplyService {
	@Autowired
	private QnareplyRepository qr;
	@Autowired
	private ModelMapper mm;
	
	public void save(QnareplyDTO QnareplyDTO) {
		qr.save(mm.map(QnareplyDTO, Qnareply.class));
	}
	public List<QnareplyDTO> findByQnarOrigin(String origin) {
		return qr.findByQnarOriginOrderByQnarNoDesc(origin).stream()
				.map(data -> mm.map(data, QnareplyDTO.class))
				.collect(Collectors.toList());
	}
	public List<QnareplyDTO> findAll() {
		return qr.findAll().stream()
				.map(data -> mm.map(data, QnareplyDTO.class))
				.collect(Collectors.toList());
	}
	public void deleteByQnarNo(Long QnarNo) {
		qr.deleteById(QnarNo);
	}
	// 댓글 작성자 찾기위해 댓글객체 얻기
	public Qnareply findByQnarNo(Long qnarNo) {
		return qr.findByQnarNo(qnarNo);
	}
}
