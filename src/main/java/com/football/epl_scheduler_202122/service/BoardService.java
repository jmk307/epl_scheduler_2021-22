package com.football.epl_scheduler_202122.service;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.dto.Board.BoardRequestDTO;
import com.football.epl_scheduler_202122.dto.Board.BoardResponseDTO;
import com.football.epl_scheduler_202122.dto.Board.SearchCondition;
import com.football.epl_scheduler_202122.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long register(BoardRequestDTO boardRequestDTO) {
        Board board = boardRequestDTO.toEntity();
        boardRepository.save(board);
        return board.getId();
    }

    // 목록
    public List<BoardResponseDTO> findBoards(String startDate, int page, Pageable pageable, SearchCondition searchCondition) {
        List<Board> search = boardRepository.search(searchCondition, startDate, PageRequest.of(page, 3));
        return search.stream().map(BoardResponseDTO::new).collect(Collectors.toList());
    }

    public BoardResponseDTO findBoard(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        return new BoardResponseDTO(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
