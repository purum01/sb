package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.QBoardEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest
public class BoardRepository3Test {

	@Autowired
	BoardRepository3 boardRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	JPAQueryFactory jpaQueryFactory;
	
	
	/*
	 * Predicate 매개 변수 활용
	 */
	@Test
	public void queryDslQueryTest1() {
		String searchCondition = "title";
		String searchKeyword = "테스트";

		QBoardEntity qboard = QBoardEntity.boardEntity;
		BooleanBuilder builder = new BooleanBuilder();

		if (searchCondition.equals("title")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if (searchCondition.equals("content")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}

		builder.and(qboard.seq.gt(0L));
		Pageable paging = PageRequest.of(0, 5);
		Page<BoardEntity> boardList = boardRepository.findAll(builder, paging);
		boardList.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void queryDslQueryTest2() {
		Predicate predicate = QBoardEntity.boardEntity.title.contains("테스트")
				              .and(QBoardEntity.boardEntity.seq.between(10, 20));
		Optional<BoardEntity>  boardList = boardRepository.findOne(predicate);
		if(boardList.isPresent()) {
			BoardEntity board = boardList.get();
			System.out.println(board);
		}
	}
	
	@Test
	public void queryDslQueryTest3() {
		QBoardEntity qboard = QBoardEntity.boardEntity;
		Iterable<BoardEntity> boardList = boardRepository.findAll(qboard.title.contains("테스트")
				                                         .and(qboard.seq.between(10, 20)));
		boardList.forEach(board-> System.out.println(board) );
	}
	
	/*
	 * JPAQuery와 JPAQueryFactory
	 */

	@Test
	public void queryDslQueryTest4() {
		JPAQuery<BoardEntity>  query = new JPAQuery<>(entityManager);
		QBoardEntity qboard = QBoardEntity.boardEntity;
		
		List<BoardEntity>  boardList = query
										.from(qboard)
										.where(qboard.title.eq("JPA"))
										.orderBy(qboard.seq.asc())
										.fetch();
		boardList.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void queryDslQueryTest5() {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
		QBoardEntity qboard = QBoardEntity.boardEntity;
		List<BoardEntity>  boardList = jpaQueryFactory.selectFrom(qboard)
										.where(qboard.title.eq("JPA"))
										.orderBy(qboard.seq.asc())
										.fetch();
		boardList.forEach(board -> System.out.println(board));
	}
	
	@Test
	public void queryDslQueryTest6() {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
		QBoardEntity qboard = QBoardEntity.boardEntity;
		List<String>  boardList = jpaQueryFactory
										.select(qboard.writer)
										.from(qboard)
										.where(qboard.title.eq("JPA"))
										.orderBy(qboard.seq.asc())
										.fetch();
		boardList.forEach(board -> System.out.println(board));
	}

	@Test
	public void queryDslQueryTest7() {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
		QBoardEntity qboard = QBoardEntity.boardEntity;
		List<Tuple>  boardList = jpaQueryFactory
										.select(qboard.writer, qboard.title)
										.from(qboard)
										.where(qboard.title.eq("JPA"))
										.orderBy(qboard.seq.asc())
										.fetch();
		boardList.forEach(board -> System.out.println(board));
	}
	
	
	/*
	 * JpaQueryFactory 자동 주입
	 */
	@Test
	public void queryDslQueryTest8() {
		QBoardEntity qboard = QBoardEntity.boardEntity;
		
		List<Tuple>  boardList = jpaQueryFactory
				.select(qboard.writer, qboard.title)
				.from(qboard)
				.where(qboard.title.eq("JPA"))
				.orderBy(qboard.seq.asc())
				.fetch();
		
		boardList.forEach(board ->{ 
			System.out.println(board.get(qboard.writer)+":"+ board.get(qboard.title));
		});
	}
	
}

















