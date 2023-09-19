package com.edu.shopapp.dto.board;

import java.time.LocalDateTime;

import com.edu.shopapp.domain.board.Board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private Long seq;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime regDate;
	
	public BoardDTO() {
		
	}
	
	public  BoardDTO(Board board) {
		this.seq = board.getSeq();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		this.regDate = board.getRegDate();
	}

}
