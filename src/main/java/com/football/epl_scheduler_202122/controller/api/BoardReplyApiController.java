package com.football.epl_scheduler_202122.controller.api;

import com.football.epl_scheduler_202122.dto.Board.Reply.BoardReplyRequestDto;
import com.football.epl_scheduler_202122.dto.Board.Reply.BoardReplyResponseDto;
import com.football.epl_scheduler_202122.service.BoardReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("boards/{startDate}/{id}")
public class BoardReplyApiController {
    private final BoardReplyService boardReplyService;

    // 댓글 등록
    @PostMapping
    public ResponseEntity<BoardReplyResponseDto> replySave(@PathVariable Long id, @RequestBody BoardReplyRequestDto boardReplyRequestDto, Authentication authentication) {
        return ResponseEntity.ok(boardReplyService.replySave(authentication.getName(), id, boardReplyRequestDto));
    }
}
