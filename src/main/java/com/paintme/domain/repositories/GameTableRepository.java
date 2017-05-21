package com.paintme.domain.repositories;

import com.paintme.domain.models.GameTable;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GameTableRepository extends CrudRepository<GameTable, Integer>{
}