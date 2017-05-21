package com.paintme.domain.repositories;

import com.paintme.domain.models.User;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.repository.CrudRepository;

@Configurable
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByLogin(String login);
    User findByLoginAndPasswordHash(String login, String passwordHash);
}