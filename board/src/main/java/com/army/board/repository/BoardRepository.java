package com.army.board.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.army.board.entity.ContentBoardEntitiy;

public interface BoardRepository<T> extends JpaRepository <ContentBoardEntitiy, Long> {

	Page<ContentBoardEntitiy> findAll(Pageable pageable);
	
}
