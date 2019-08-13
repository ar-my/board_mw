package com.army.board.controller;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.army.board.entity.ContentBoardEntitiy;
import com.army.board.service.BoardService;
import com.army.board.service.BoardServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardController {

//	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private BoardService<ContentBoardEntitiy> bService;
	private BoardServiceImpl bServiceImpl;
//	private Page<ContentBoardEntitiy> page;
	// 필드 인젝션
//	@Autowired
//	private BoardService bService;
//	@Autowired
//	private BoardServiceImpl bServiceImpl;

	List<ContentBoardEntitiy> list;

//	public BoardController(BoardService bService) {
//		this.bService = bService;
//	}

	// 생성자를 통한 인젝션
	public BoardController(BoardServiceImpl bServiceImpl) {
		this.bService = bServiceImpl;
	}

	// setter 인젝션
//	@Autowired   //autowired 필수 
//	public void setBoardService(BoardServiceImpl bServiceImpl) {
//		this.bService = bServiceImpl;
//	}

//	@RequestMapping("/select")
//	public ResponseEntity<?> contentList() throws Exception {
//
//		list = bService.getSelect();
//		return ResponseEntity.ok(list);
//
//	}
	@RequestMapping("/select")
	public ResponseEntity<?> contentList() throws Exception {
		
		list = bService.getSelect();
		URI uri = URI.create(String.format("/select", list));
		return ResponseEntity.created(uri).body(list);
		
	}

	@PostMapping("/select/paging")
	public Page<ContentBoardEntitiy> contentList(
			@PageableDefault(sort = { "id" }, direction = Direction.ASC, size = 10) Pageable pageable) {
		log.info("pageable : " + pageable);
		return bService.getSelect(pageable);
	}

//	@RequestMapping("/select/page")
//	public Page<ContentBoardEntitiy> list() throws Exception{
////		Page<ContentBoardEntitiy> page = bService.getBoardPaging();
////		list = bService.getSelect();
//		return bService.getBoardPaging();
//	}

	@PostMapping("/insert")
	// 처리한 쿼리문이 정상적으로 완료가 되고, companyDAO.saveextensionTarget 에서 처리 도중 에러가 났을 때
	// 처리한 쿼리를 자동 rollback 해주기 위해 사용된다.

	@Transactional
	public ResponseEntity<?> contentInsert(ContentBoardEntitiy input) throws Exception {
		log.info("aa");

//		bService.setData(input);
//		return ResponseEntity.ok(list);
		URI uri = URI.create(String.format("/insert", input));
		return ResponseEntity.created(uri).body(input);
	}
	
	@PostMapping("/insert/jedis")
	@Transactional
	public ResponseEntity<?> jedisInsert(ContentBoardEntitiy input) throws Exception {
		log.info("data : " + input);
		bService.setJedis(input);

		return ResponseEntity.ok(list);
	}

	@PostMapping("/insert/lettuce")
	@Transactional
	public ResponseEntity<?> lettuceInsert(ContentBoardEntitiy input) throws Exception {
		log.info("data : " + input);
		bService.setLettuce(input);

		return ResponseEntity.ok(list);
	}

	@PostMapping("/delete")
	@Transactional
	public String contentDelete(Long id) throws Exception {
		log.info("aa");

		bService.delete(id);
//		boardRepository.delete(id);
		return "삭제하였습니다.";
	}

	@PostMapping("/update")
	@Transactional
	public String contentUpdate(ContentBoardEntitiy data) throws Exception {
		log.info("aa");

		bService.update(data);

		return "수정하였습니다.";
	}

	@PostMapping("/count")
	@Transactional
	public Long contentCount() throws Exception {
		log.info("aa");

		Long count = bService.count();

		return count;
	}
}
