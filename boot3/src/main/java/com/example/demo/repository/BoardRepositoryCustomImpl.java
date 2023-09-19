package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.QBoardEntity;



public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

	public BoardRepositoryCustomImpl() {
		super(BoardEntity.class);
	}

	@Override
	public List<BoardEntity> findByWriter(String writer) {
		QBoardEntity board = QBoardEntity.boardEntity;
		List<BoardEntity> boardList = from(board).where(board.writer.eq(writer)).select(board).fetch();
		return boardList;
	}

}
