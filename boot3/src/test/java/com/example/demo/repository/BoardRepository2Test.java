package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.entity.BoardEntity;

@SpringBootTest
@EnableJpaAuditing
public class BoardRepository2Test {

	@Autowired
	private BoardRepository2 boardRepository;

	@Test
	public void create() {
		int i = 200;
		List<BoardEntity> list = new ArrayList<>();
		while (i-- > 0) {
			BoardEntity board = new BoardEntity();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCnt(i);
			list.add(board);
		}
		boardRepository.saveAll(list);
	}

	@Test
	public void getBoardListTest() {
		assertThat(boardRepository.getBoardList().size()).isEqualTo(19);
	}

	@Test
	public void findAllTest() {
		assertThat(boardRepository.findAll().size()).isEqualTo(19);
	}

	@Test
	public void getBoardListTest2() {
		List<BoardEntity> boardList = boardRepository.getBoardList2("JPA");
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	public void getBoardListTest3() {
		List<BoardEntity> boardList = boardRepository.getBoardList3("JPA");
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	public void getBoardListTest4() {
		List<Object[]> result = boardRepository.getBoardList4("JPA");
		result.forEach((row) -> {
			System.out.println(Arrays.toString(row));
		});
	}

	@Test
	public void getBoardListTest5() {
		List<Object[]> result = boardRepository.getBoardList5("JPA");
		result.forEach((row) -> {
			System.out.println(Arrays.toString(row));
		});
	}

	@Test
	public void getBoardListTest6() {
		Pageable paging = PageRequest.of(0, 3);
		List<BoardEntity> boardList = boardRepository.getBoardList6(paging);
		boardList.forEach(board -> System.out.println(board));
	}

}
