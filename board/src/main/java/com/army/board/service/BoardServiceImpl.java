package com.army.board.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.army.board.entity.ContentBoardEntitiy;
import com.army.board.repository.BoardRepository;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	private BoardRepository boardRepository;
	RedisTemplate<String, Object> redisTemplate;
	RedisConnectionFactory redisLettuce;
	
//    RedisCommands<String, S> redisCommands;

	private BoardServiceImpl(BoardRepository boardRepository, RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory redisLettuce) {
		this.boardRepository = boardRepository;
		this.redisTemplate = redisTemplate;
		this.redisLettuce = redisLettuce;
	}

	private ContentBoardEntitiy user;

	@Override
	public List<?> getSelect() {

		List<ContentBoardEntitiy> list = boardRepository.findAll();

		return list;
	}
	
	@Override
	public Page<ContentBoardEntitiy> getSelect(Pageable pageable) {
//		@RequestMapping("/list")
//		public String list(Model model,
//				@PageableDefault(sort = { "id" }, direction = Direction.DESC, size = 2) Pageable pageable) {
//			Page<Post> postPage = postDao.findAll(pageable);
//			model.addAttribute("postPage", postPage);
//			User user = getConnect();
//			model.addAttribute("user", user);
//			return "list";
//		}
		return boardRepository.findAll(pageable);
	}

	
	@Override
	public void setJedis(ContentBoardEntitiy data) {
		log.info("redisTemplate : " + redisTemplate);
		log.info("data : " + data);
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		value.set("contentBoard", data.toString());
		log.info("redis set : " + (String) value.get("contentBoard"));

	}
	
	@Override
	public void setLettuce(ContentBoardEntitiy data) {
//		StatefulRedisConnection<String, String> connection = (StatefulRedisConnection<String, String>) redisLettuce.getConnection();
////		RedisConnection connection = redisLettuce.getConnection();
//		RedisCommands<String, String> syncCommands = (RedisCommands<String, String>) connection.async();
//		log.info("redisLettuce : " + redisLettuce);
//		log.info("redisCommands : " + redisCommands);
//		redisCommands.set("lettuce", data);
//		StatefulRedisConnection<String, String> ss = (StatefulRedisConnection<String, String>) redisLettuce.getConnection();
//		RedisCommands<String, String> syncCommands = ss.sync();
//		syncCommands.set("key", data.toString());
		
		RedisClient redisClient = RedisClient.create("redis://localhost:6379/0");
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> syncCommands = connection.sync();
		syncCommands.set("key", data.toString());
		log.info("data : " + data);
		
	}

	@Override
	public void setData(ContentBoardEntitiy data) {

		boardRepository.save(data);
	}

	@Override
	public void delete(Long id) {
		user = (ContentBoardEntitiy) boardRepository.getOne(id);
		boardRepository.deleteById(id);

	}

	@Override
	public void update(ContentBoardEntitiy data) {
		ContentBoardEntitiy user = (ContentBoardEntitiy) boardRepository.getOne(data.getId());
		user.setContents(data.getContents());
		user.setNickname(data.getNickname());
		user.setRegdate(data.getRegdate());
		user.setTitle(data.getTitle());

	}

	@Override
	public Long count() {
		Long count = boardRepository.count();

		return count;
	}
	
}
