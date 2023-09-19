package com.example.demo.repository;

import java.util.List;
import com.example.demo.entity.BoardEntity;

public interface BoardRepositoryCustom {

	List<BoardEntity> findByWriter(String writer);

}
