package com.project2.hct.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.dto.CertDTO;
import com.project2.hct.entity.Certificate;
import com.project2.hct.repository.CertificateRepo;

@Service
public class CertificateService {

	@Autowired
	CertificateRepo certificateRepo;

	public List<Certificate> getCert(String memId) {
		return certificateRepo.findAllByCertMemid(memId);
	}

	public void update(CertDTO certDTO) {
		// 업데이트 대상 찾기
		Optional<Certificate> opCert = certificateRepo.findById(certDTO.getCertNo());
		
		if(opCert.isPresent()) {
			Certificate cert = opCert.get();
			// 업데이트할 필드만 설정
			cert.setCertName(certDTO.getCertName());
			cert.setCertDepart(certDTO.getCertDepart());
			cert.setCertReg(certDTO.getCertReg());
			//update
			certificateRepo.save(cert);
		}else {
		}
	}

	public void insert(CertDTO certDTO) {
		Certificate cert = new Certificate();
		cert.setCertMemid(certDTO.getCertMemid());
		cert.setCertName(certDTO.getCertName());
		cert.setCertDepart(certDTO.getCertDepart());
		cert.setCertReg(certDTO.getCertReg());
		
		certificateRepo.save(cert);
	}
	
}
