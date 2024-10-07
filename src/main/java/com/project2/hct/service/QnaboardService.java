package com.project2.hct.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project2.hct.dto.QnaboardDTO;
import com.project2.hct.entity.Qnaboard;
import com.project2.hct.repository.QnaboardRepository;

@Service
public class QnaboardService {
	@Autowired
	private QnaboardRepository qr;
	@Autowired
	private ModelMapper mm;
	
	public QnaboardDTO findByQnaNo(Long no) {
		return mm.map(qr.findByQnaNo(no), QnaboardDTO.class);
	}
	
	public List<QnaboardDTO> findAll() {
		return qr.findAll().stream()
				.map(data -> mm.map(data, QnaboardDTO.class))
				.collect(Collectors.toList());
	}
	public void save(QnaboardDTO qna) {
		qr.save(mm.map(qna, Qnaboard.class));
	}
	public Page<Qnaboard> list(int page){
		return qr.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "qnaNo")));
	}

	public void delete(Long qnaNo) {
		qr.deleteById(qnaNo);
	}

}
