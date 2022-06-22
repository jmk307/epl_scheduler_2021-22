package com.football.epl_scheduler_202122.service;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.dto.Board.BoardRequestDTO;
import com.football.epl_scheduler_202122.dto.Board.BoardResponseDTO;
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

    public List<BoardResponseDTO> findBoards(String startDate) {
        List<Board> boards = boardRepository.findAll(startDate);
        return boards.stream()
                .map(BoardResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Page<BoardResponseDTO> findPageBoards(String startDate, Pageable pageable) {
        Page<BoardResponseDTO> pageBoards = boardRepository.findByStartDate(startDate, pageable);
        return new PageImpl<>(pageBoards.getContent(), pageable, pageBoards.getTotalElements());
    }

    public BoardResponseDTO findBoard(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        return new BoardResponseDTO(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
