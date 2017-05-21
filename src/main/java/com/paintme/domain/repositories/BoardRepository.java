package com.paintme.domain.repositories;

import com.paintme.domain.models.Board;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board, Integer>{
}