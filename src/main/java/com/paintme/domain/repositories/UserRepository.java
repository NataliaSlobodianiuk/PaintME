package com.paintme.domain.repositories;

import com.paintme.domain.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByLogin(String login);
    User findByLoginAndPasswordHash(String login, String passwordHash);
}