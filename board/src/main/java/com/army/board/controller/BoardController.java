package com.army.board.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.army.board.common.ContentBoard;
import com.army.board.repository.BoardRepository;


@RestController
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@RequestMapping("/select")
	public List contentList(Integer pageNum) {
		System.out.println("aaaa");
		List<ContentBoard> list = boardRepository.findAll();
		
		return list;
	}
	
	@RequestMapping("/insert")
	@Transactional
	public String contentInsert(ContentBoard input) {
	
		SimpleDateFormat sf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		
		String regdate = sf.format (System.currentTimeMillis());
		        	
		ContentBoard contentBoard = new ContentBoard();
		contentBoard.setRegdate(regdate);
		boardRepository.save(input);
	
		return "등록하였습니다.";
	}
	
	@RequestMapping("/delete")
	@Transactional
	public String contentDelete(ContentBoard id) {
		System.out.println("cccc");
		boardRepository.delete(id);
		return "삭제하였습니다.";
	}
	
	@RequestMapping("/update")
	@Transactional
	public String contentUpdate(ContentBoard id) {
		System.out.println("cccc");
		boardRepository.save(id);
		return "변하였습니다.";
	}
	
	@RequestMapping("/count")
	@Transactional
	public Long contentCount() {
		System.out.println("cccc");
		Long count = boardRepository.count();
		
		return count;
	}
}
