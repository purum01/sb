package com.edu.shopapp.service.board;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.shopapp.domain.board.Board;
import com.edu.shopapp.domain.board.BoardRepository;
import com.edu.shopapp.dto.board.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService{
	
	private final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class); 
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Transactional
	public void insertBoard(BoardDTO dto) {
		LOGGER.info("insertBoard()가 호출되었습니다");
		
		Board board = new Board();
		board.setTitle(dto.getTitle());
		board.setContent(dto.getContent());
		board.setWriter(dto.getWriter());
		
		boardRepo.save(board);
	}
	
	@Transactional
	public void updateBoard(BoardDTO dto) {
		LOGGER.info("updateBoard()가 호출되었습니다");
		
		Board board = boardRepo.findById(dto.getSeq()).orElseThrow(IllegalArgumentException::new);
		board.setTitle(dto.getTitle());
		board.setContent(dto.getContent());		
	}
	
	@Transactional
	public void deleteBoard(Long seq) {
		LOGGER.info("deleteBoard()가 호출되었습니다");
		
		Board board = boardRepo.findById(seq).orElseThrow(IllegalArgumentException::new);
		boardRepo.delete(board);
	}
	
	
	public BoardDTO getBoard(Long seq) {
		LOGGER.info("getBoard()가 호출되었습니다");

		Board board = boardRepo.findById(seq).get();
		return  new BoardDTO(board);
	}
	
	@Override
	public List<BoardDTO> getBoardList() {
		LOGGER.info("getBoardList()가 호출되었습니다");
		
		return boardRepo.findAll().stream()
				.map(BoardDTO::new)
				.collect(Collectors.toList());
		
	}
}
