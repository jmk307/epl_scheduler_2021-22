package com.football.epl_scheduler_202122.controller.api;

import com.football.epl_scheduler_202122.dto.Board.BoardResponseDto;
import com.football.epl_scheduler_202122.dto.Board.Search.SearchCondition;
import com.football.epl_scheduler_202122.dto.Board.Search.SearchType;
import com.football.epl_scheduler_202122.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "boards/{startDate}")
public class BoardApiController {

    private final BoardService boardService;

    // 초기화면
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> main(@PathVariable String startDate,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       Pageable pageable,
                                                       @RequestParam(required = false) SearchType searchType,
                                                       @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(boardService.findBoards(startDate, page, pageable, new SearchCondition(keyword, searchType)), HttpStatus.OK);
    }
}
