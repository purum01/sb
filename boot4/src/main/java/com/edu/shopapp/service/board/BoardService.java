package com.edu.shopapp.service.board;

import java.util.List;

import com.edu.shopapp.dto.board.BoardDTO;

public interface BoardService {

	void insertBoard(BoardDTO dto);

	void updateBoard(BoardDTO dto);

	void deleteBoard(Long seq);

	BoardDTO getBoard(Long seq);

	List<BoardDTO> getBoardList();
}
