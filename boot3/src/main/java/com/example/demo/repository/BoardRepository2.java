package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.BoardEntity;

public interface BoardRepository2 extends JpaRepository<BoardEntity, Long> {

	@Query("select b from BoardEntity b where seq > 0 order by seq")
	List<BoardEntity> getBoardList();

	@Query("select b from BoardEntity b where b.title like %?1% order by b.seq desc")
	List<BoardEntity> getBoardList2(String keyword);

	@Query("select b from BoardEntity b where b.title like %:keyword% order by b.seq desc")
	List<BoardEntity> getBoardList3(String keyword);

	@Query("select b.seq, b.title, b.writer from BoardEntity b where b.title like %?1% order by b.seq desc")
	List<Object[]> getBoardList4(String keyword);

	@Query(value = "select seq, title, writer from board  where title like '%'||?1||'%' order by seq desc", nativeQuery = true)
	List<Object[]> getBoardList5(String keyword);

	// 페이징 처리
	@Query("select b from BoardEntity b order by b.seq desc")
	List<BoardEntity> getBoardList6(Pageable paging);
}
