package com.project2.hct.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project2.hct.entity.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

	//회원 아이디 중복 확인
	boolean existsBymemId(String memId);
	
	//memId를 가지고 회원정보 조회 (로그인), 내정보/비번확인(수정,탈퇴)
	Member findBymemId(String memId);

	//임시닉네임 중복확인
	Member findBymemNickname(String nickname);

	//비밀번호 찾기 전 회원이 입력한 아이디와 이름을 가지고 회원정보 리턴
	@Query(value="select * from member where mem_id= :memId and mem_name = :memName", nativeQuery=true)
	Member checkid_name(@Param("memId") String memId, @Param("memName") String memName);

	//아이디 찾기 전 회원이 입력한 이름과 전화번호를 가지고 회원정보 리턴
	@Query(value="select * from member where mem_name= :memName and mem_tel = :memTel", nativeQuery=true)
	Member checkname_tel(@Param("memName") String memName, @Param("memTel") String memTel);

	Page<Member> findByMemRole(String memRole, Pageable pageable);

}
