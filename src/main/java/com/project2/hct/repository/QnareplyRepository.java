package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.hct.entity.Qnareply;

public interface QnareplyRepository  extends JpaRepository<Qnareply, Long>{
	public List<Qnareply> findAll();

	public List<Qnareply> findByQnarOrigin(String origin);
	public List<Qnareply> findByQnarOriginOrderByQnarNoDesc(String origin);
	//public void update();

	public Qnareply findByQnarNo(long QnarNo);
}
