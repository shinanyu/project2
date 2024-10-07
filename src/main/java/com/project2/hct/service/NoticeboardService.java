package com.project2.hct.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project2.hct.entity.Noticeboard;
import com.project2.hct.repository.NoticeboardRepository;

@Service
public class NoticeboardService {
	@Autowired
	private NoticeboardRepository dao;
	
	public List<Noticeboard> getMainList() {
		return dao.findmainboard();
	}
	// 모든 공지사항을 조회하는 메서드
    public List<Noticeboard> findAllNotices() {
        return dao.findAll();
    }

    // ID로 특정 공지사항을 조회하는 메서드
    public Noticeboard findNoticeById(Long id) {
        return dao.findByNbNo(id);
    }

    // 공지사항 추가 메서드
    public void saveNotice(Noticeboard notice) {
        dao.save(notice);
    }

    // 공지사항 수정 메서드
    public void updateNotice(Noticeboard updatedNotice) {
        if (updatedNotice.getNbNo() != null) {
            Optional<Noticeboard> existingNotice = dao.findById(updatedNotice.getNbNo());
            if (existingNotice.isPresent()) {
                Noticeboard notice = existingNotice.get();
                notice.setNbTitle(updatedNotice.getNbTitle());
                notice.setNbContent(updatedNotice.getNbContent());
                dao.save(notice);
            }
        }
    }

    // 공지사항 삭제 메서드
    public void deleteNotice(Long id) {
        dao.deleteById(id);
    }

    // 모든 공지사항을 페이징 처리하여 조회하는 메서드
    public Page<Noticeboard> findNoticesWithPaging(int page, int size) {
        return dao.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "nbNo")));
    }
}