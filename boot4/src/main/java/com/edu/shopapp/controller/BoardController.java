package com.edu.shopapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.shopapp.dto.board.BoardDTO;
import com.edu.shopapp.service.board.BoardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/board")
public class BoardController {

	private final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@PostMapping(value = "/insert")
	public void insertBoard(@RequestBody BoardDTO dto) {
		LOGGER.info("insertBoard()가 호출되었습니다");
		LOGGER.info("BoardDTO:" + dto);
		boardService.insertBoard(dto);
	}

	@PutMapping(value = "/update")
	public void updateBoard(@RequestBody BoardDTO dto) {
		LOGGER.info("updateBoard()가 호출되었습니다");
		boardService.updateBoard(dto);

	}

	@DeleteMapping(value = "/delete/{seq}")
	public void deleteBoard(@PathVariable Long seq) {
		LOGGER.info("deleteBoard()가 호출되었습니다");
		boardService.deleteBoard(seq);
	}

	@GetMapping(value = "/get/{seq}")
	public BoardDTO getBoard(@PathVariable Long seq) {
		LOGGER.info("getBoard()가 호출되었습니다");
		return boardService.getBoard(seq);
	}

	@GetMapping(value = "/list")
	public List<BoardDTO> getBoardList() {
		LOGGER.info("getBoardList()가 호출되었습니다");
		return boardService.getBoardList();
	}

	@ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
	@GetMapping(value = "/request")
	public String getRequestParam(
	    @ApiParam(value = "이름", required = true) @RequestParam String name,
	    @ApiParam(value = "이메일", required = true) @RequestParam String email,
	    @ApiParam(value = "회사", required = true) @RequestParam String organization) {

	    return name + " " + email + " " + organization;
	}


}
