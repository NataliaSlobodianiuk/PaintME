package com.paintme.domain.repositories;

import com.paintme.domain.models.Team;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer>{
}
