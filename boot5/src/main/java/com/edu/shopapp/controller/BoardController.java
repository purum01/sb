package com.edu.shopapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.shopapp.dto.board.BoardDTO;
import com.edu.shopapp.service.board.BoardService;

@Controller
public class BoardController {

	private final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@RequestMapping("/")
	public String index() {
		LOGGER.info("index()가 호출되었습니다");
		return "redirect:getBoardList";
	}

	@GetMapping(value = "/getBoardList")
	public void getBoardList(Model model) {
		LOGGER.info("getBoardList()가 호출되었습니다");
		List<BoardDTO> bordList = boardService.getBoardList();
		model.addAttribute("boardList", bordList);
	}

	@GetMapping(value = "/getBoard")
	public String getBoard(Long seq, Model model) {
		LOGGER.info("getBoard()가 호출되었습니다");
		BoardDTO boardDto = boardService.getBoard(seq);
		model.addAttribute("board", boardDto);
		return "getBoard";
	}

	@GetMapping(value = "insertBoard")
	public String insertBoard() {
		LOGGER.info("Get방식으로 getBoard()가 호출되었습니다");
		return "insertBoard";
	}

	@PostMapping(value = "insertBoard")
	public String insertBoard(BoardDTO boardDto) {
		LOGGER.info("Post방식으로 getBoard()가 호출되었습니다");
		boardService.insertBoard(boardDto);
		return "redirect:getBoardList";
	}

	@GetMapping(value = "deleteBoard")
	public String deleteBoard(Long seq) {
		LOGGER.info("deleteBoard()가 호출되었습니다");
		boardService.deleteBoard(seq);
		return "redirect:getBoardList";
	}

	@PostMapping(value = "updateBoard")
	public String updateBoard(BoardDTO boardDto) {
		LOGGER.info("updateBoard()가 호출되었습니다");
		boardService.updateBoard(boardDto);
		return "redirect:getBoardList";
	}
}
