package com.paintme.repositories;

import org.springframework.data.repository.*;

import com.paintme.models.*;

public interface TeamRepository extends CrudRepository<Team, Integer>{
}