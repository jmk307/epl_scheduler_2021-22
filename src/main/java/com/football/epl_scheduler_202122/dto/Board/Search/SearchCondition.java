package com.football.epl_scheduler_202122.dto.Board.Search;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class SearchCondition {

    private String content;

    private SearchType type;

    public SearchCondition(String content, SearchType type) {
        this.content = content;
        this.type = type;
    }
}
