package com.football.epl_scheduler_202122.dto.Board.Reply;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.domain.BoardReply;
import com.football.epl_scheduler_202122.domain.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReplyRequestDto {
    // 댓글 내용
    private String reply;

    private Board board;

    private Member member;

    public BoardReply toEntity() {
        return BoardReply.builder()
                .reply(reply)
                .board(board)
                .member(member)
                .build();
    }
}
