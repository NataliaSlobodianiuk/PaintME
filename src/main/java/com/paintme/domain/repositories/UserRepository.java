package com.paintme.domain.repositories;

import org.springframework.data.repository.*;

import com.paintme.domain.models.*;

public interface UserRepository extends CrudRepository<User, Integer>{
    User findByLogin(String login);
}