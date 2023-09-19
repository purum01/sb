package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.BoardEntity;

@EnableJpaAuditing
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;

	/*
	 * JpaRepository에서 제공하는 메소드 테스트
	 */

	@Test
	@DisplayName("게시글 등록이 정상 동작한다")
	public void insertBoardTest() {
		BoardEntity entity = new BoardEntity();
		entity.setTitle("JPA");
		entity.setWriter("이순신");
		entity.setContent("자바 표준 ORM 스펙입니다");

		boardRepository.save(entity);
	}

	@Test
	@DisplayName("게시글 추출이 정상 동작한다")
	public void getBoardTest() {
		Optional<BoardEntity> result = boardRepository.findById(1L);
		result.ifPresent((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("게시글 수정이 정상 동작한다")
	public void updateBoardTest() {
		Optional<BoardEntity> result = boardRepository.findById(1L);
		BoardEntity board = result.get();
		board.setTitle("AWS");
		board.setContent("아마존 클라우드 서비스");
		boardRepository.save(board);
	}

	@Test
	@DisplayName("게시글 전체 조회가 정상 동작한다")
	public void getBoardListTest() {
		List<BoardEntity> result = boardRepository.findAll();
		result.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("게시글 삭제가 정상 동작한다")
	public void deleteBoardTest() {
		boardRepository.deleteById(2L);

		assertFalse(boardRepository.findById(2L).isPresent());
	}

	/*
	 * Query Method 테스트
	 */
	@Test
	@DisplayName("Writer 조건으로 조회가 정상 동작한다")
	public void findByWriterTest() {
		int i = 10;
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

		List<BoardEntity> boardList = boardRepository.findByWriter("테스터");
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("Writer 조건으로 삭제가 정상 동작한다")
	public void deleteAllTest() {
		List<BoardEntity> boardList = boardRepository.findByWriter("테스터");
		boardRepository.deleteAll(boardList);
	}

	@Test
	@DisplayName("Title 일부조건으로 조회가 정상 동작한다")
	public void findByTitleContainingTest() {
		List<BoardEntity> boardList = boardRepository.findByTitleContaining("테스트");
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("cnt 크기비교 조건으로 조회가 정상 동작한다")
	public void findByCntGreaterThanTest() {
		List<BoardEntity> boardList = boardRepository.findByCntGreaterThan(5);
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("cnt 범위지정 조건으로 조회가 정상 동작한다")
	public void findByCntBetweenTest() {
		List<BoardEntity> boardList = boardRepository.findByCntBetween(3, 6);
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("Title과 Content OR 조건으로 조회가 정상 동작한다")
	public void findByTitleContainingOrContentContainingTest() {
		List<BoardEntity> boardList = boardRepository.findByTitleContainingOrContentContaining("JPA", "JPA");
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("Title과 Content AND 조건으로 조회가 정상 동작한다")
	public void findByTitleContainingAndContentContainingTest() {
		List<BoardEntity> boardList = boardRepository.findByTitleContainingAndContentContaining("JPA", "JPA");
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("Writer IN 조건으로 조회가 정상 동작한다")
	public void findByWriterInTest() {
		List<String> writers = Arrays.asList("홍길동", "이순신", "유관순");
		List<BoardEntity> boardList = boardRepository.findByWriterIn(writers);
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	/*
	 * 
	 */
	@Test
	@DisplayName("exists~By : 특정 데이터가 존재하는지 확인한다")
	public void existsByWriterTest() {
		assertTrue(boardRepository.existsByWriter("홍길동"));
	}

	@Test
	@DisplayName("조회 쿼리를 수행한 후 쿼리 결과로 나온 레코드수를 리턴한다")
	public void countByWriterTest() {
		assertThat(boardRepository.countByWriter("홍길동")).isEqualTo(1);
	}

	@Test
	@Transactional
	@DisplayName("삭제쿼리 수행. 리턴값이 없거나 삭제한 횟수 리턴한다")
	public void deleteByWriterTest() {
		boardRepository.deleteByWriter("김푸름");
		assertThat(boardRepository.existsByWriter("김푸름")).isFalse();
	}

	@Test
	@DisplayName("쿼리를 통해 조회된 결과값의 개수를 제한한다")
	public void findFirst5ByWriterTest() {
		List<BoardEntity> boardList = boardRepository.findFirst5ByWriter("테스터");
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	/*
	 * 데이터 정렬
	 */

	@Test
	@DisplayName("Cnt 정렬 조건으로 조회가 정상 동작한다")
	public void findByWriterInOrderByCntDescTest() {
		List<String> writers = Arrays.asList("홍길동", "이순신", "유관순");
		List<BoardEntity> boardList = boardRepository.findByWriterInOrderByCntDesc(writers);
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("Sort 매개변수 정렬 조건이 정상 동작한다")
	public void findByWriterInSortTest() {
		List<BoardEntity> boardList = boardRepository.findByWriter("홍길동", Sort.by(Sort.Direction.DESC, "cnt"));
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("Sort 매개변수 2차 정렬 조건이 정상 동작한다")
	public void findAllTest() {
		List<BoardEntity> boardList = boardRepository.findAll(Sort.by(Sort.Order.desc("cnt"), Sort.Order.asc("seq")));
		boardList.forEach((board) -> {
			System.out.println(board);
		});
	}

	@Test
	@DisplayName("페이징 처리 조건이 정상 동작한다")
	public void findAllPagingTest() {
		// Page<BoardEntity> pageInfo = boardRepository.findAll(PageRequest.of(0, 5));
		Page<BoardEntity> pageInfo = boardRepository.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "cnt"));
		pageInfo.forEach((board) -> {
			System.out.println(board);
		});

		System.out.println("PAGE SIZE :" + pageInfo.getSize());
		System.out.println("TOTAL PAGES :" + pageInfo.getTotalPages());
		System.out.println("TOTAL COUNT :" + pageInfo.getTotalElements());
		System.out.println("NEXT :" + pageInfo.nextPageable());
	}

}
