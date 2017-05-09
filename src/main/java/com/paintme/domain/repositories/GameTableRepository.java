package com.paintme.domain.repositories;

import org.springframework.data.repository.*;

import com.paintme.domain.models.*;

public interface GameTableRepository extends CrudRepository<GameTable, Integer>{
}