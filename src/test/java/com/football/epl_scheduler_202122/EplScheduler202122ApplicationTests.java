package com.football.epl_scheduler_202122;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.repository.Board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class EplScheduler202122ApplicationTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void save() {
        Board board = Board.builder()
                .id(36L)
                .home("123")
                .away("456")
                .startDate("2026-08-24")
                .startTime(null)
                .result("2:4")
                .build();

        boardRepository.save(board);

        Board result = boardRepository.findById(board.getId()).get();
        assertThat(board).isEqualTo(result);
    }
}
