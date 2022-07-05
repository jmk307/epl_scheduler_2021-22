package com.football.epl_scheduler_202122.repository;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.dto.Board.SearchCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardCustomRepository {

    List<Board> search(SearchCondition searchCondition, String startDate, Pageable pageable);

}
