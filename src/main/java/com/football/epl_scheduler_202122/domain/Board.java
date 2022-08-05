package com.football.epl_scheduler_202122.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    // 게시물이 삭제되면 댓글 또한 삭제되어야 하기 때문에 CascadeType.REMOVE로 설정
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<BoardReply> boardReplyList;

    public void update(String home, String away, String startDate, Date startTime, String result) {
        this.home = home;
        this.away = away;
        this.startDate = startDate;
        this.startTime = startTime;
        this.result = result;
    }
}
