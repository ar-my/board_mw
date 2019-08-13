package com.army.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.army.board.entity.ContentBoardEntitiy;

public interface BoardService<T> {

	public List<T> getSelect();
//	public Page<ContentBoardEntitiy> getSelect(Pageable pageable);
	public void setData(ContentBoardEntitiy data);
	public void update(ContentBoardEntitiy data);
	public Long count();
	public void delete(Long id);
	public void setJedis(ContentBoardEntitiy data);
	public void setLettuce(ContentBoardEntitiy data);
	Page<ContentBoardEntitiy> getSelect(Pageable pageable);
}
