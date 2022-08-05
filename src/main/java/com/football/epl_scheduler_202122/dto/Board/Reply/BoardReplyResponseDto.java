package com.football.epl_scheduler_202122.dto.Board.Reply;

import com.football.epl_scheduler_202122.domain.BoardReply;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardReplyResponseDto {
    // 댓글 id
    private Long boardReplyId;

    // 댓글 내용
    private String reply;
    
    // 댓글 작성자
    private String replier;

    // 해당 게시판 id
    private Long id;

    public BoardReplyResponseDto(BoardReply boardReply) {
        this.boardReplyId = boardReply.getBoardReplyId();
        this.reply = boardReply.getReply();
        this.replier = boardReply.getMember().getNickname();
        this.id = boardReply.getBoard().getId();
    }
}
