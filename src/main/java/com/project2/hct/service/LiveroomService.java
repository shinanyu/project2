package com.project2.hct.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.dto.LiveroomDTO;
import com.project2.hct.entity.Liveroom;
import com.project2.hct.repository.LiveroomRepository;

@Service
public class LiveroomService {
	@Autowired
	private LiveroomRepository liveroomrepository;
	@Autowired
	private ModelMapper mm;
	
	public void save(LiveroomDTO liveroomDTO) {
		//모델맵퍼 -> dto를 entity로 변환
		liveroomrepository.save(mm.map(liveroomDTO, Liveroom.class));
	}
	
	public void delete(String live_clno) {	
		liveroomrepository.delete(live_clno);
	}

	public List<Liveroom> getList() {
		return liveroomrepository.findroom();
	}

	public boolean existsByLiveClno(String liveClno) {
		return liveroomrepository.existsByLiveClno(liveClno);
	}
	
	public void updateThumbnail(String clNo, String originalFilename) {
		liveroomrepository.updateThumbnail(clNo, originalFilename);
	}
	
}