package com.football.epl_scheduler_202122.service;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.domain.BoardReply;
import com.football.epl_scheduler_202122.domain.Member;
import com.football.epl_scheduler_202122.dto.Board.Reply.BoardReplyRequestDto;
import com.football.epl_scheduler_202122.dto.Board.Reply.BoardReplyResponseDto;
import com.football.epl_scheduler_202122.repository.Board.BoardReplyRepository;
import com.football.epl_scheduler_202122.repository.Board.BoardRepository;
import com.football.epl_scheduler_202122.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardReplyService {
    private final BoardReplyRepository boardReplyRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 댓글 등록
    @Transactional
    public BoardReplyResponseDto replySave(String username, Long id, BoardReplyRequestDto boardReplyRequestDto) {
        Member member = memberRepository.findByUsername(username);
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패!"));

        boardReplyRequestDto.setMember(member);
        boardReplyRequestDto.setBoard(board);

        BoardReply boardReply = boardReplyRequestDto.toEntity();
        boardReplyRepository.save(boardReply);

        return new BoardReplyResponseDto(boardReply);
    }






}
