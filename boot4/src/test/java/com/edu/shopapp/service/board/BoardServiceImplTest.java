package com.edu.shopapp.service.board;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.edu.shopapp.dto.board.BoardDTO;

@SpringBootTest
@EnableJpaAuditing
public class BoardServiceImplTest {
	
	@Autowired
	private BoardService boardService;
	
	
	@Test
	public void insertBoardTest() {
		BoardDTO boardDto = new BoardDTO();
		boardDto.setTitle("MVC");
		boardDto.setWriter("홍길동");
		boardDto.setContent("Model, View, Controller 디자인 패턴이다");
		boardService.insertBoard(boardDto);

		BoardDTO boardDto2 = new BoardDTO();
		boardDto2.setTitle("JPA");
		boardDto2.setWriter("이순신");
		boardDto2.setContent("자바진영의 ORM 표준이다");
		boardService.insertBoard(boardDto2);
	}
	
	@Test
	public void updateBoardTest() {
		BoardDTO boardDto = new BoardDTO();
		boardDto.setSeq(2L);
		boardDto.setTitle("Spring");
		boardDto.setWriter("유관순");
		boardDto.setContent("Backend Java 프레임워크이다");
		
		boardService.updateBoard(boardDto);
	}
	
	@Test
	public void deleteBoardTest() {
		boardService.deleteBoard(1L);
	}
	
	@Test
	public void getBoardTest() {
		BoardDTO boardDto=boardService.getBoard(1L);
		System.out.println(boardDto);
	}
	
	@Test
	public void getBoardListTest() {
		List<BoardDTO> boardList = boardService.getBoardList();
		boardList.forEach(board->System.out.println(board));
	}

}
