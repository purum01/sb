package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	List<BoardEntity> findByTitle(String title);

	List<BoardEntity> findByWriter(String title);

	List<BoardEntity> findByContent(String title);

	List<BoardEntity> findByTitleContaining(String title);

	List<BoardEntity> findByCntGreaterThan(int cnt);

	List<BoardEntity> findByCntBetween(int start, int end);

	List<BoardEntity> findByTitleContainingOrContentContaining(String title, String content);

	List<BoardEntity> findByTitleContainingAndContentContaining(String title, String content);

	List<BoardEntity> findByWriterIn(List<String> writers);
	
	//exists~By : 특정 데이터가 존재하는지 확인
	boolean existsByWriter(String writer);
	
	//count~By : 조회 쿼리를 수행한 후 쿼리 결과로 나온 레코드수를 리턴
	long countByWriter(String writer);
	
	//delete~By, remove~By : 삭제쿼리 수행. 리턴값이 없거나 삭제한 횟수 리턴
	void deleteByWriter(String writer);
	long removeByWriter(String writer);
	
	//~First<number~, ~Top<number>~  : 쿼리를 통해 조회된 결과값의 개수를 제한
	List<BoardEntity> findFirst5ByWriter(String writer);
	List<BoardEntity> findTop10ByWriter(String writer);
	
	//정렬, 페이징 처리
	List<BoardEntity> findByWriterInOrderByCntDesc(List<String> writers);
	
	List<BoardEntity> findByWriter(String writers, Sort sort);
	
	Page<BoardEntity> findAll(Pageable pageable);

}
