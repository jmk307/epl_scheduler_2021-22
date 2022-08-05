package com.football.epl_scheduler_202122.service;

import com.football.epl_scheduler_202122.domain.Board;
import com.football.epl_scheduler_202122.dto.Board.BoardRequestDto;
import com.football.epl_scheduler_202122.dto.Board.BoardResponseDto;
import com.football.epl_scheduler_202122.dto.Board.BoardUpdateRequestDto;
import com.football.epl_scheduler_202122.dto.Board.Search.SearchCondition;
import com.football.epl_scheduler_202122.repository.Board.BoardCustomRepository;
import com.football.epl_scheduler_202122.repository.Board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardCustomRepository boardCustomRepository;

    public BoardResponseDto register(BoardRequestDto boardRequestDTO) {
        Board board = boardRequestDTO.toEntity();
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    // 목록
    public List<BoardResponseDto> findBoards(String startDate, int page, Pageable pageable, SearchCondition searchCondition) {
        List<Board> search = boardCustomRepository.search(searchCondition, startDate, PageRequest.of(page, 3));
        return search.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    public BoardResponseDto findBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new BoardResponseDto(board);
    }

    public BoardResponseDto updateBoard(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        board.update(boardUpdateRequestDto.getHome(), boardUpdateRequestDto.getAway(), boardUpdateRequestDto.getStartDate(), boardUpdateRequestDto.getStartTime(), boardUpdateRequestDto.getResult());

        boardRepository.save(board);

        return new BoardResponseDto(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
