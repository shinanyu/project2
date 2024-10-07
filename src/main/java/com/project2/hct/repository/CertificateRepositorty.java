package com.project2.hct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Certificate;

@Repository
public interface CertificateRepositorty extends JpaRepository<Certificate, Integer>{

	
}
