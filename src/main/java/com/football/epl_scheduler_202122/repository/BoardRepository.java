package com.football.epl_scheduler_202122.repository;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.dto.Board.BoardResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {

    List<BoardResponseDTO> findAllByStartDate(@Param("startDate") String startDate, Pageable pageable);

}
