package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.hct.entity.Qnaboard;

public interface QnaboardRepository extends JpaRepository<Qnaboard, Long>{
	List<Qnaboard> findAll();
	
	Qnaboard findByQnaNo(Long qna_no);
	
	Page<Qnaboard> findAll(Pageable pageable);

	List<Qnaboard> findAllByqnaWriter(String memId);

}
