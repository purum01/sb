package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.BoardEntity;

@SpringBootTest
public class BoardRepository4Test {

	@Autowired
	BoardRepository4 boardRepository;

	@Test
	public void findByWriterTest() {
		List<BoardEntity> boardList = boardRepository.findByWriter("이순신");
		boardList.forEach(board -> System.out.println(board));
	}

}
