package com.project2.hct.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.hct.dto.JoinDTO;
import com.project2.hct.entity.Healthclass;
import com.project2.hct.entity.Member;
import com.project2.hct.entity.Myclass;
import com.project2.hct.entity.Qnaboard;
import com.project2.hct.entity.Wishlist;
import com.project2.hct.repository.HealthclassRepo;
import com.project2.hct.repository.MemberRepository;
import com.project2.hct.repository.MypageMycrepo;
import com.project2.hct.repository.MypageWishrepo;
import com.project2.hct.repository.QnaboardRepository;

@Service
public class MypageService {
	
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private MypageMycrepo mypageMycrepo;
	@Autowired
	private MypageWishrepo mypageWishrepo;
	@Autowired
	private HealthclassRepo healthclassRepo;
	@Autowired
	private QnaboardRepository qnaRepo;

	// 특정 id 찾기
	public Member getMember(String id) {
		return memberRepo.findBymemId(id);
	}
	// 특정 회원 업데이트
	public void update(JoinDTO joinDTO) {
		// 업데이트 할 멤버 찾기
		Optional<Member> opMem = memberRepo.findById(joinDTO.getMemId());
		
		if(opMem.isPresent()) {
			Member member = opMem.get();
			// 업데이트할 필드만 설정
			member.setMemName(joinDTO.getMemName());
			member.setMemNickname(joinDTO.getMemNickname());
			member.setMemBirth(joinDTO.getMemBirth());
			member.setMemTel(joinDTO.getMemTel());
			//update
			memberRepo.save(member);
		}else {
		}
	}
	// 멤버 탈퇴
	public void deleteMember(String id) {
		// 업데이트 할 멤버 찾기
		Optional<Member> opMem = memberRepo.findById(id);
		
		if(opMem.isPresent()) {
			Member member = opMem.get();
			// 탈퇴만 설정
			member.setMemActive("n");
			// update
			memberRepo.save(member);
		}else {
		}
	}
	// 비밀번호 변경
	public void changPw(JoinDTO joinDTO) {
		// 업데이트 할 멤버 찾기
		Optional<Member> opMem = memberRepo.findById(joinDTO.getMemId());
		
		if(opMem.isPresent()) {
			Member member = opMem.get();
			// 업데이트할 필드만 설정
			member.setMemPw(joinDTO.getMemPw());
			//update
			memberRepo.save(member);
		}else {
		}
	}
	// 중복검사
	public boolean existsBymemId(String name) {
		return false;
	}
	// 내 수강 목록 구하기
	public List<Myclass> getList(String memId) {
		return mypageMycrepo.findAllBymycMemid(memId);
	}
	// 내 수강 목록 삭제
	public void deleteClass(long no) {
		mypageMycrepo.deleteById(no);
	}
	// 관심 목록 구하기
	public List<Wishlist> getWishList(String memId) {
		return mypageWishrepo.findAllBywishMemid(memId);
	}
	// 관심 목록 삭제
	public void deleteWish(long no) {
		mypageWishrepo.deleteById(no);
	}
	// 관심 수업 정보 구하기(수강 등록 하기 위해)
	public Optional<Wishlist> getWishClass(long no) {
		return mypageWishrepo.findById(no);
	}
	// 수강목록 등록
	public void regClass(Myclass myc) {
		mypageMycrepo.save(myc);
	}
	// 내가 쓴 글 목록 구하기
	public List<Qnaboard> getWriteList(String memId) {
		return qnaRepo.findAllByqnaWriter(memId);
	}
	// 내가 개설한 강의 리스트
	public List<Healthclass> getMyClass(String memId) {
		return healthclassRepo.findAllByclMemid(memId);
	}
	// 관심목록 추가
	public void saveWish(Wishlist wish) {
		mypageWishrepo.save(wish);
	}
	// 수강리스트 중복검사
	public Myclass searchclno(String clNo, String id) {
		return mypageMycrepo.searchclno(clNo, id);
	}
	// 관심리스트 중복검사
	public Wishlist searchwishno(String clNo, String id) {
		return mypageWishrepo.searchwishno(clNo,id);
	}
}
