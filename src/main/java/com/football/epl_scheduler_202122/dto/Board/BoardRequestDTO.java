package com.football.epl_scheduler_202122.dto.Board;

import com.football.epl_scheduler_202122.domain.Board;
import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardRequestDTO {
    private Long id;

    private String home;

    private String away;

    private String startDate;

    private Date startTime;

    private String result;

    public Board toEntity() {
        return Board.builder()
                .id(id)
                .home(home)
                .away(away)
                .startDate(startDate)
                .startTime(startTime)
                .result(result)
                .build();
    }
}
