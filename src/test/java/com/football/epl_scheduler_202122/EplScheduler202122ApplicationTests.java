package com.football.epl_scheduler_202122;

import com.football.epl_scheduler_202122.dto.Board.BoardResponseDTO;
import com.football.epl_scheduler_202122.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@SpringBootTest
class EplScheduler202122ApplicationTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void paging() {

        String startDate = "2021-08-14";

        PageRequest pageable = PageRequest.of(0, 10);

        Page<BoardResponseDTO> result = boardRepository.findByStartDate(startDate, pageable);

        System.out.println("----------------------");
        System.out.println(result.getTotalPages());
        System.out.println(result.getNumber());
        System.out.println("----------------------");

    }

}
