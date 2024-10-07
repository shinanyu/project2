package com.project2.hct.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.dto.ClassnoticeDTO;
import com.project2.hct.entity.Classnotice;
import com.project2.hct.repository.ClassnoticeRepository;

@Service
public class ClassnoticeService {
	
	@Autowired
	private ClassnoticeRepository cr;
	@Autowired
	private ModelMapper mm;
	
	public void save(ClassnoticeDTO classnoticeDTO) {
		cr.save(mm.map(classnoticeDTO, Classnotice.class));
	}
	public List<ClassnoticeDTO> findByClnClno(String ClnClno) {
		return cr.findByClnClnoOrderByClnNoDesc(ClnClno).stream()
				.map(data -> mm.map(data, ClassnoticeDTO.class))
				.collect(Collectors.toList());
	}
	public List<ClassnoticeDTO> findAll() {
		return cr.findAll().stream()
				.map(data -> mm.map(data, ClassnoticeDTO.class))
				.collect(Collectors.toList());
	}
	public void delete(Long clNo) {
		cr.deleteById(clNo);
		
	}
}
