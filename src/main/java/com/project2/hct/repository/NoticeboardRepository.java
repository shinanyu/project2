package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Noticeboard;

@Repository
public interface NoticeboardRepository extends JpaRepository<Noticeboard, Long>{
	
	@Query(value="select * from noticeboard order by nb_no desc limit 0, 10", nativeQuery = true)
	public List<Noticeboard> findmainboard();
	Noticeboard findByNbNo(Long id);

    // 모든 공지사항을 페이징 처리하여 조회하는 메서드
    Page<Noticeboard> findAll(Pageable pageable);
}