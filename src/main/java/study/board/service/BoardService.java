package study.board.service;

import org.springframework.stereotype.Service;
import study.board.dto.BoardDto;
import study.board.repository.BoardRepository;

import javax.transaction.Transactional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
}
