package com.football.epl_scheduler_202122.controller.view;

import com.football.epl_scheduler_202122.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping(value = "boards")
public class BoardViewController {

    private static final String MAIN = "index";

    private final BoardService boardService;

    // 초기화면
    @GetMapping(value = "list")
    public String main(Model model, String startDate) {
        model.addAttribute("list", boardService.findBoards(startDate));
        return MAIN;
    }

    // 목록 타임리프
    @GetMapping(value = "list/{startDate}")
    public String list(Model model, @PathVariable String startDate, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("boards", boardService.findPageBoards(startDate, pageable));
        return MAIN;
    }
}
