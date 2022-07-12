package com.football.epl_scheduler_202122.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String home;

    private String away;

    private String startDate;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    private String result;

    public void update(String home, String away, String startDate, Date startTime, String result) {
        this.home = home;
        this.away = away;
        this.startDate = startDate;
        this.startTime = startTime;
        this.result = result;
    }
}
