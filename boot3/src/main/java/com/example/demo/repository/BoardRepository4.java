package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BoardEntity;

public interface BoardRepository4 extends JpaRepository<BoardEntity, Long>, BoardRepositoryCustom  {

}
