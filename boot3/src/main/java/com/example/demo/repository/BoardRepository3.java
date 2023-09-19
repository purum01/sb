package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.demo.entity.BoardEntity;


public interface BoardRepository3 extends JpaRepository<BoardEntity, Long>, QuerydslPredicateExecutor<BoardEntity> {

}