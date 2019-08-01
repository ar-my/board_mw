package com.army.board.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.army.board.common.ContentBoard;

public interface BoardRepository extends JpaRepository <ContentBoard, Long> {
	
}
