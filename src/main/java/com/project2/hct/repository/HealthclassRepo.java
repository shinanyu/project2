package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project2.hct.entity.Healthclass;

@Repository
public interface HealthclassRepo extends JpaRepository<Healthclass, Long> {

	List<Healthclass> findAllByclMemid(String memId);
	
	public List<Healthclass> findAll();
	public Healthclass findByClNo(Long clNo);
	
	@Transactional
	@Modifying
	@Query(value="update healthclass set cl_thumbnail = null where cl_no = :cl_no",nativeQuery=true)
	public void updateThumbnailNull(@Param("cl_no") String cl_no);
	
	@Transactional
	@Modifying
	@Query(value="update healthclass set cl_profile = null where cl_no = :cl_no",nativeQuery=true)
	public void updateProfileNull(@Param("cl_no") String cl_no);
	
	@Transactional
	@Modifying
	@Query(value="delete from healthclass where cl_no=:cl_no", nativeQuery=true)
	public int delete(@Param("cl_no") String cl_no);
	
	@Query(value="select MAX(cl_no) from healthclass", nativeQuery=true)
	public int getLastClNo();
	
	@Query(value="select * from healthclass where cl_no= :cl_no", nativeQuery=true)
	public Healthclass getlist(@Param("cl_no") String cl_no);
	
	@Query(value="select count(*) from healthclass", nativeQuery=true)
	public int getTotal();
	
	@Query(value="select count(*) from healthclass where cl_type = :cl_type", nativeQuery=true)
	public int getTotal(@Param("cl_type") String cl_type);
	
	@Query(value="select count(*) from healthclass where cl_name = :cl_name", nativeQuery=true)
	public int getTotalBySearchAll_Classname(@Param("cl_name") String cl_name);
	
	@Query(value="select count(*) from healthclass where cl_memname = :cl_memname", nativeQuery=true)
	public int getTotalBySearchAll_Memname(@Param("cl_memname") String cl_memname);
	
	@Query(value="select count(*) from healthclass where cl_day = :cl_day", nativeQuery=true)
	public int getTotalBySearchAll_Day(@Param("cl_day") String cl_day);
	
	@Query(value="select count(*) from healthclass where cl_type = :cl_type and cl_name like %:cl_name%", nativeQuery=true)
	public int getTotalBySearch_Classname(@Param("cl_type") String cl_type,
			@Param("cl_name") String cl_name);
	
	@Query(value="select count(*) from healthclass where cl_type = :cl_type and cl_memname = :cl_memname", nativeQuery=true)
	public int getTotalBySearch_Memname(@Param("cl_type") String cl_type,
			@Param("cl_memname") String cl_memname);
	
	@Query(value="select count(*) from healthclass where cl_type = :cl_type and cl_Day = :cl_Day", nativeQuery=true)
	public int getTotalBySearch_Day(@Param("cl_type") String cl_type,
			@Param("cl_Day") String cl_Day);
	
	@Query(value="select * from healthclass order by cl_no DESC limit :startRow , :endRow", nativeQuery=true)
	public List<Healthclass> select(@Param("startRow") int startRow,
									@Param("endRow") int endRow);
	
	@Query(value="select * from healthclass where cl_type= :cl_type order by cl_no DESC limit :startRow , :endRow", nativeQuery=true)
	public List<Healthclass> selectCategory(
											@Param("startRow") int startRow,
											@Param("endRow") int endRow,
											@Param("cl_type") String cl_type);
	
	@Query(value="select * from healthclass order by cl_no DESC limit :startRow , :endRow", nativeQuery=true)
	public List<Healthclass> selectSearch(@Param("startRow") int startRow,
			@Param("endRow") int endRow);
	
	@Query(value="select * from healthclass order by cl_no DESC", nativeQuery=true)
	public List<Healthclass> selectMain();
	
	@Query(value="select * from healthclass where cl_name like %:cl_name% order by cl_no DESC", nativeQuery=true)
	public List<Healthclass> selectSearchAll_Classname(@Param("cl_name") String cl_name);

	@Query(value="select * from healthclass where cl_memname = :cl_memname order by cl_no DESC", nativeQuery=true)
	public List<Healthclass> selectSearchAll_Memname(@Param("cl_memname") String cl_memname);

	@Query(value="select * from healthclass where cl_day = :cl_day order by cl_no DESC", nativeQuery=true)
	public List<Healthclass> selectSearchAll_Day(@Param("cl_day") String cl_day);
	
	@Query(value="select * from healthclass where cl_type = :cl_type and cl_name like %:cl_name% order by cl_no DESC", nativeQuery=true)
	public List<Healthclass> selectSearch_Classname(@Param("cl_type") String cl_type,
			@Param("cl_name") String cl_name);

	@Query(value="select * from healthclass where cl_type = :cl_type and cl_memname= :cl_memname order by cl_no DESC", nativeQuery=true)
	public List<Healthclass> selectSearch_Memname(@Param("cl_type") String cl_type,
			@Param("cl_memname") String cl_memname);
	
	@Query(value="select * from healthclass where cl_type = :cl_type and cl_day= :cl_day order by cl_no DESC", nativeQuery=true)
	public List<Healthclass> selectSearch_Day(@Param("cl_type") String cl_type,
			@Param("cl_day") String cl_day);
	
	@Transactional
	@Modifying
	@Query(value="update healthclass set cl_profile = :cl_profile where cl_no = :cl_no", nativeQuery=true)
	public void updateProfile(@Param("cl_no") String cl_no, @Param("cl_profile") String cl_profile);
	
	@Transactional
	@Modifying
	@Query(value="update healthclass set cl_thumbnail = :cl_thumbnail where cl_no = :cl_no", nativeQuery=true)
	public void updateThumbnail(@Param("cl_no") String cl_no, @Param("cl_thumbnail") String cl_thumbnail);
	

}
