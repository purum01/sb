package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "board")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seq;
	private String title;
	private String writer;
	private String content;

	@Column(name = "regdate", columnDefinition = "TIMESTAMP DEFAULT NOW()")
	@CreatedDate
	private LocalDateTime regDate;

	@Column(columnDefinition = "NUMBER(5) DEFAULT 0")
	private int cnt;
}
