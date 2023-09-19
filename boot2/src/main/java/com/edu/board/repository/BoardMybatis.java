package com.edu.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.edu.board.vo.BoardVO;


public interface BoardMybatis {

	public void insertBoard(BoardVO vo);

	public void updateBoard(BoardVO vo);

	public void deleteBoard(BoardVO vo);

	public BoardVO getBoard(BoardVO vo);

	public List<BoardVO> getBoardList(BoardVO vo);

}
