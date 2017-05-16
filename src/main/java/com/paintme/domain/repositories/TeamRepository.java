package com.paintme.domain.repositories;

import org.springframework.data.repository.*;

import com.paintme.domain.models.*;

public interface TeamRepository extends CrudRepository<Team, Integer>{
}