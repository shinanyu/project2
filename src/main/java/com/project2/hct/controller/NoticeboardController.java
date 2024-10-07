package com.project2.hct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project2.hct.entity.Noticeboard;
import com.project2.hct.service.NoticeboardService;


@Controller
public class NoticeboardController {
    @Autowired
    private NoticeboardService service;

    // 공지사항 목록을 보여주는 메서드, 페이징 처리 추가
    @GetMapping("/noticelist")
    public String listNotices(@RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "10") int size,
                              Model model) {
        // 페이징 처리된 공지사항 목록 가져오기
        Page<Noticeboard> noticesPage = service.findNoticesWithPaging(page, size);
        model.addAttribute("notices", noticesPage.getContent());
        model.addAttribute("totalPages", noticesPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "noticelist/list";  // Thymeleaf 템플릿 폴더 내의 'list.html'
    }

    // 공지사항 상세 정보를 보여주는 메서드
    @GetMapping("/noticelist/detail/{id}")
    public String noticeDetail(@PathVariable("id") Long id, Model model) {
        Noticeboard notice = service.findNoticeById(id);
        if (notice != null) {
            model.addAttribute("notice", notice);
            return "noticelist/detail";  // 템플릿 폴더 'detail.html'
        }
        return "redirect:/noticelist";  // 상세 정보를 찾지 못했을 때 목록으로 리다이렉트
    }

    // 공지사항 수정 폼 페이지로 이동
    @GetMapping("/noticelist/admin/edit/{id}")
    public String editNotice(@PathVariable("id") Long id, Model model) {
        Noticeboard notice = service.findNoticeById(id);
        if (notice != null) {
            model.addAttribute("notice", notice);
            return "noticelist/edit";  // 템플릿 폴더 'edit.html'
        }
        return "redirect:/noticelist";
    }

    // 공지사항 수정 처리
    @PostMapping("/noticelist/admin/update")
    public String updateNotice(Noticeboard notice, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "noticelist/edit";
        }
        System.out.println("title=" + notice.getNbTitle());
        service.updateNotice(notice);
        redirectAttributes.addFlashAttribute("successMessage", "공지사항이 성공적으로 수정되었습니다.");
        return "redirect:/noticelist";
    }

    // 공지사항 추가 폼 페이지로 이동
    @GetMapping("/noticelist/admin/add")
    public String addNoticeForm(Model model) {
        model.addAttribute("notice", new Noticeboard());
        return "noticelist/add";
    }

    // 공지사항 추가 처리
    @PostMapping("/noticelist/admin/save")
    public String saveNotice(@ModelAttribute Noticeboard notice, RedirectAttributes redirectAttributes) {
        service.saveNotice(notice);
        redirectAttributes.addFlashAttribute("successMessage", "새 공지사항이 추가되었습니다.");
        return "redirect:/noticelist";
    }

    // 공지사항 삭제 처리
    @PostMapping("/noticelist/admin/delete/{id}")
    public String deleteNotice(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        service.deleteNotice(id);
        redirectAttributes.addFlashAttribute("successMessage", "공지사항이 삭제되었습니다.");
        return "redirect:/noticelist";
    }
}
