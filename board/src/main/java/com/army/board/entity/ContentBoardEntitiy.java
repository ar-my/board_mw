package com.army.board.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="content_board")
@Data
public class ContentBoardEntitiy implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nickname", nullable = false)
	private String nickname;
	
	@Column(name = "regdate", nullable = false)
	private String regdate;

	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "contents", nullable = false)
	private String contents;

	
}
