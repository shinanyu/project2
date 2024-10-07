package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Myclass;

@Repository
public interface MypageMycrepo extends JpaRepository<Myclass, Long> {

	List<Myclass> findAllBymycMemid(String memid);

	@Query(value="select * from myclass where myc_clno = :clNo and myc_memid = :id", nativeQuery = true)
	Myclass searchclno(@Param("clNo") String clNo, @Param("id") String id);
	
}
