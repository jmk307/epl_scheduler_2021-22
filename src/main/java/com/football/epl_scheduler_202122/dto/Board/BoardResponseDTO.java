package com.football.epl_scheduler_202122.dto.Board;

import com.football.epl_scheduler_202122.domain.Board;
import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@Builder
public class BoardResponseDTO {
    private Long id;

    private String home;

    private String away;

    private String startDate;

    private Date startTime;

    private String result;

    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.home = board.getHome();
        this.away = board.getAway();
        this.startDate = board.getStartDate();
        this.startTime = board.getStartTime();
        this.result = board.getResult();
    }
}
