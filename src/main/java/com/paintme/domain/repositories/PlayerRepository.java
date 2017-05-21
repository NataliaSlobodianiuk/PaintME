package com.paintme.domain.repositories;

import com.paintme.domain.models.Player;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>{
}