package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Liveroom;

import jakarta.transaction.Transactional;

@Repository
public interface LiveroomRepository extends JpaRepository<Liveroom, Long>{
	
	@Query(value="select * from liveroom", nativeQuery = true)
	public List<Liveroom> findroom();
	
	@Transactional
	@Modifying
	@Query(value="delete from liveroom where live_clno=:live_clno", nativeQuery=true)
	public int delete(@Param("live_clno") String live_clno);
	
	public boolean existsByLiveClno(String liveClno);
	
	@Transactional
	@Modifying
	@Query(value="update liveroom set live_thumbnail = :liveThumbnail where live_clno = :liveClno", nativeQuery=true)
	public void updateThumbnail(@Param("liveClno") String liveClno, @Param("liveThumbnail") String liveThumbnail);

}