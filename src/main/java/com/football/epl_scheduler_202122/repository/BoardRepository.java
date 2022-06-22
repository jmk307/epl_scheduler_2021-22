package com.football.epl_scheduler_202122.repository;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.dto.Board.BoardResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b where b.startDate = :startDate")
    List<Board> findAll(@Param("startDate") String startDate);

    Page<BoardResponseDTO> findByStartDate(String startDate, Pageable pageable);
}
