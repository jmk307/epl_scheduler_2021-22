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

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    private String result;
}
