package com.football.epl_scheduler_202122.dto.Board;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.dto.Board.Reply.BoardReplyResponseDto;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
@AllArgsConstructor
@Builder
public class BoardResponseDto {
    private Long id;

    private String home;

    private String away;

    private String startDate;

    private Date startTime;

    private String result;

    private List<BoardReplyResponseDto> replies;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.home = board.getHome();
        this.away = board.getAway();
        this.startDate = board.getStartDate();
        this.startTime = board.getStartTime();
        this.result = board.getResult();
        this.replies = board.getBoardReplyList().stream().map(BoardReplyResponseDto::new).collect(Collectors.toList());
    }
}
