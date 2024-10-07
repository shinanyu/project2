package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Myclass;
import com.project2.hct.entity.Wishlist;

@Repository
public interface MypageWishrepo extends JpaRepository<Wishlist, Long> {

	List<Wishlist> findAllBywishMemid(String memId);
	
	@Query(value="select * from wishlist where wish_clno = :clNo and wish_memid = :id", nativeQuery = true)
	Wishlist searchwishno(@Param("clNo") String clNo, @Param("id") String id);

}
