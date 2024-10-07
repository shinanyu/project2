package com.project2.hct.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project2.hct.dto.CertDTO;
import com.project2.hct.dto.HealthclassDTO;
import com.project2.hct.dto.JoinDTO;
import com.project2.hct.entity.Certificate;
import com.project2.hct.entity.Healthclass;
import com.project2.hct.entity.Member;
import com.project2.hct.entity.Myclass;
import com.project2.hct.entity.Qnaboard;
import com.project2.hct.entity.Wishlist;
import com.project2.hct.service.CertificateService;
import com.project2.hct.service.HealthclassService;
import com.project2.hct.service.MypageService;

@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	@Autowired
	private HealthclassService healthclassService;
	@Autowired
	private CertificateService certificateSv;
	
	// 마이페이지로 이동
	@GetMapping("mypageL")
	public String mypage(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/mypage";
		}else {
			return "redirect:/main";
		}
		
	}
	
	// 수강목록 보기
	@GetMapping("classListP")
	public String classListP(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/classListPage";
		}else {
			return "redirect:/main";
		}
	}
	
	// 관심목록 보기
	@GetMapping("wishListP")
	public String wishListP(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/wishListPage";
		}else {
			return "redirect:/main";
		}
	}
	
	// 내글목록 보기
	@GetMapping("writeListP")
	public String writeListP(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/writeListPage";
		}else {
			return "redirect:/main";
		}
	}
	
	// 내강의목록 보기
	@GetMapping("myClassP")
	public String myClassP(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/myClassPage";
		}else {
			return "redirect:/main";
		}
	}
	
	// 내자격증 보기
	@GetMapping("updateCert")
	public String updateCert(Authentication auth, Model model) {
		
		if(auth != null) {
			String id = auth.getName();
			List<Certificate> list = certificateSv.getCert(id);
			model.addAttribute("list", list);
			return "mypage/updateCert";
		}else {
			return "redirect:/main";
		}
	}
	
	// 수정폼 불러오기
	@GetMapping("updateMemForm/{memId}")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> updateform(@PathVariable("memId") String memId){
		
		System.out.println("수정폼 id : "+memId);
		Member editm = mypageService.getMember(memId);
		
		Map map = new HashMap<>();
		map.put("editm", editm);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 회원정보 수정
	@PostMapping("member_edit.do")
	public String member_edit_ok(JoinDTO joinDTO,Authentication auth) throws Exception {
		
		if ( auth == null) {
			return "redirect:/main";
		}
		
		String id = auth.getName();
		String pwd = mypageService.getMember(id).getMemPw();

		// 입력한 PW 와 암호화된 PW 비교해주는 matches() 메소드로 암호 일치 여부 확인
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(joinDTO.getMemPw(), pwd);

		if (!isPasswordMatch) {   // 비번 불일치시
			System.out.println("불일치");
			return "redirect:/main";
		} else {										// 비번 일치시			
			System.out.println("일치");
			mypageService.update(joinDTO);
			return "mypage/mypage";
		}
		
	}
	
	// 탈퇴 페이지
	@GetMapping("deleteMember")
	public String leaveMem(Authentication auth,Model model) {
		if (auth != null) {
			String id = auth.getName();
			
			model.addAttribute("memId",id);
			return "mypage/deleteMember";
		}else {
			return "redirect:/main";
		}
	}
	
	// 회원정보 삭제
	@PostMapping("deleteMember.do")
	public String memberDel(@RequestParam("memPw") String memPw, Authentication auth) throws Exception {
		
		if ( auth == null) {
			return "redirect:/main";
		}
		
		String id = auth.getName();
		String pwd = mypageService.getMember(id).getMemPw();
		
		// 입력한 PW 와 암호화된 PW 비교해주는 matches() 메소드로 암호 일치 여부 확인
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(memPw, pwd);

		if (!isPasswordMatch) {   // 비번 불일치시
			System.out.println("불일치");
			return "mypage/deleteMember";
		} else {										// 비번 일치시			
			System.out.println("일치");
			mypageService.deleteMember(id);	// delete SQL문 실행
			return "redirect:/logout";
		}
	}
	
	// 비밀번호변경 페이지
	@GetMapping("changePw")
	public String changePwpage(Authentication auth,Model model) {
		if (auth != null) {
			String id = auth.getName();
			model.addAttribute("memId",id);
			return "mypage/changePw";
		}else {
			return "redirect:main";
		}
	}
	
	// 비밀번호 변경
	@PostMapping("changePw.do")
	public String changePw(JoinDTO joinDTO, Authentication auth) throws Exception {
		
		if ( auth == null) {
			return "redirect:/main";
		}
		
		String id = auth.getName();
		String pwd = mypageService.getMember(id).getMemPw();
		
		// 입력한 PW 와 암호화된 PW 비교해주는 matches() 메소드로 암호 일치 여부 확인
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean isPasswordMatch = passwordEncoder.matches(joinDTO.getMemPw(), pwd);

		if (!isPasswordMatch) {   // 비번 불일치시
			return "mypage/changePw";
		} else {										// 비번 일치시			
			// 비번 암호화
			String encryptedPw = passwordEncoder.encode(joinDTO.getMemChangePw());
			joinDTO.setMemPw(encryptedPw);
			mypageService.changPw(joinDTO);	// delete SQL문 실행
			return "mypage/mypage";
		}
		
	}
	
	// 수강 목록
	@GetMapping("getClassList/{memId}")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> getClassList(@PathVariable("memId") String memId){
		
		List<Myclass> list = mypageService.getList(memId);
		
		Map map = new HashMap<>();
		map.put("list", list);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 수강 취소
	@GetMapping("deleteClass/{no}")
	@ResponseBody
	public void deleteClass(@PathVariable("no") long no) {
		mypageService.deleteClass(no);
	}
	
	// 관심 목록
	@GetMapping("getWishList/{memId}")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> getWishList(@PathVariable("memId") String memId){
		
		List<Wishlist> list = mypageService.getWishList(memId);
		
		Map map = new HashMap<>();
		map.put("list", list);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 관심 삭제
	@GetMapping("deleteWish/{no}")
	@ResponseBody
	public void deleteWish(@PathVariable("no") long no) {
		mypageService.deleteWish(no);
	}
	
	// 관심목록에서 수강목록 등록
	@GetMapping("regClass/{no}")
	@ResponseBody
	public int regClass(@PathVariable("no") long no,Authentication auth, Model model) throws Exception {
		int result = 0;
		
		Optional<Wishlist> wish = mypageService.getWishClass(no);
		Wishlist wishEnt = wish.get();
		String clno = wishEnt.getWishClno();
		String id = auth.getName();
		
		// 중복검사
		Myclass isReg = mypageService.searchclno(clno,id);
		if(isReg != null) {
			model.addAttribute("id", id);
			return result;
		}else {
			if(wish.isPresent()) {
				Myclass myc = new Myclass();
				myc.setMycClmemname(wishEnt.getWishClmemname());
				myc.setMycClname(wishEnt.getWishClname());
				myc.setMycClno(wishEnt.getWishClno());
				myc.setMycMemid(wishEnt.getWishMemid());
				mypageService.regClass(myc);
			} else {
				throw new Exception();
			}
			result = 1;
			model.addAttribute("memId",id);
			return result;
		}
	}
	
	// 쓴글 목록
	@GetMapping("getWriteList/{memId}")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> getWriteList(@PathVariable("memId") String memId){
		
		List<Qnaboard> list = mypageService.getWriteList(memId);
		
		Map map = new HashMap<>();
		map.put("list", list);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 개설 강의 목록
	@GetMapping("getMyClass/{memId}")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> getMyClass(@PathVariable("memId") String memId){
		
		List<Healthclass> list = mypageService.getMyClass(memId);
		
		Map map = new HashMap<>();
		map.put("list", list);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 수강신청
	@GetMapping("/mypage/regClass/{cl_no}")
	public String regiClass(@PathVariable("cl_no") String clno,Authentication auth,Model model) {
		String id = auth.getName();
		
		// 중복검사
		Myclass isReg = mypageService.searchclno(clno,auth.getName());
		if(isReg != null) {
			model.addAttribute("clno", clno);
			return "mypage/regClassFail";
		}else {
			Myclass myc = new Myclass();
			HealthclassDTO hsDTO = healthclassService.findByClNo(clno);
			
			myc.setMycMemid(id);
			myc.setMycClname(hsDTO.getClName());
			myc.setMycClmemname(hsDTO.getClMemname());
			myc.setMycClno(clno);
			
			mypageService.regClass(myc);

			model.addAttribute("clno", clno);
			return "mypage/regClassSuccess";
		}
		
	}
	
	// 관심등록
	@GetMapping("/mypage/regWish/{cl_no}")
	public String regWish(@PathVariable("cl_no") String clno,Authentication auth,Model model) {
		String id = auth.getName();
		
		// 중복검사
		Wishlist isReg = mypageService.searchwishno(clno,auth.getName());
		if(isReg != null) {
			model.addAttribute("clno", clno);
			return "mypage/regClassFail";
		}else {
			Wishlist wish = new Wishlist();
			HealthclassDTO hsDTO = healthclassService.findByClNo(clno);
			
			wish.setWishMemid(id);
			wish.setWishClname(hsDTO.getClName());
			wish.setWishClmemname(hsDTO.getClMemname());
			wish.setWishClno(clno);
			
			mypageService.saveWish(wish);
			
			model.addAttribute("clno", clno);
			return "mypage/regClassSuccess";
		}
	}
	
	// 자격증 수정
	@PostMapping("/updateRegCert")
	public String updateRegCert(Authentication auth,CertDTO certDTO) {
		
		if ( auth == null) {
			return "redirect:/main";
		}
		
		certificateSv.update(certDTO);
		return "mypage/updateSuccess";
	}
	
	// 자격증 추가 페이지
	@GetMapping("/addCert")
	public String addCert() {
		return "mypage/addCert";
	}
	
	// 자격증 추가
	@PostMapping("/RegCert")
	public String regCert(Authentication auth,CertDTO certDTO) {
		certDTO.setCertMemid(auth.getName());
		certificateSv.insert(certDTO);
		
		return "mypage/updateSuccess";
	}
	
}
