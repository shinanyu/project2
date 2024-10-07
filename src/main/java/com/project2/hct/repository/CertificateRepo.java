package com.project2.hct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Certificate;

@Repository
public interface CertificateRepo extends JpaRepository<Certificate, Long>{

	List<Certificate> findAllByCertMemid(String memId);

}