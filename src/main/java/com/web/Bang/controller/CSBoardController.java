package com.web.Bang.controller;

import com.web.Bang.auth.PrincipalDetail;
import com.web.Bang.dto.queryDslDto.CustomServiceBoardDto;
import com.web.Bang.model.CustomServiceBoard;
import com.web.Bang.model.type.RoleType;
import com.web.Bang.service.CSBoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CSBoardController {
    private final CSBoardServiceImpl csBoardService;

    @GetMapping("/user/cs")
    public String intoCustomerService(
            @PageableDefault(direction = Direction.DESC, sort = "id") Pageable pageable, String q,
            Model model) {
        String st = (q == null) ? "" : q;
        Page<CustomServiceBoardDto> boards = csBoardService.findByTitle(st, pageable);
        List<CustomServiceBoardDto> noticeBoards = csBoardService.loadNoticeBoards();
        model.addAttribute("notices", noticeBoards);
        model.addAttribute("boards", boards);
        model.addAttribute("pageNums", makePageNumbers(boards));
        return "/customerService/customerService";
    }

    private ArrayList<Integer> makePageNumbers(Page<CustomServiceBoardDto> pages) {
        int nowPage = pages.getPageable().getPageNumber();
        int startPage = Math.max(nowPage - 2, 0); // 두 인트값 중에 큰 값을 반환 한다.
        int endPage = Math.min(nowPage + 2, pages.getTotalPages() - 1);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            list.add(i);
        }
        System.out.println(list);
        return list;
    }

    @GetMapping("/guest/cs-write")
    public String writeCustomerBoard() {
        return "/customerService/customerBoardwrite";
    }

    @GetMapping("/guest/cs-update/{id}")
    public String updateCustomerBoard(@PathVariable int id, Model model) {
        CustomServiceBoard csboard = csBoardService.findCSboardByid(id);
        model.addAttribute("board", csboard);
        return "/customerService/customerBoardupdate";
    }

    @GetMapping("/guest/cs/detail/{id}")
    public String showDetail(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principalDetail,
                             Model model) {
        CustomServiceBoard csboard = csBoardService.findCSboardByid(id);
        if (csboard.getSecret() == 1) {
            if (principalDetail.getUser().getRole() != RoleType.ADMIN && csboard.getUser().getId() != principalDetail.getUser().getId()) {
                return "redirect:/user/cs";
            }
        }
        model.addAttribute("board", csboard);
        return "/customerService/customerBoardDetail";
    }

}
