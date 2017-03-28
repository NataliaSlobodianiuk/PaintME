package com.paintme.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.*;

import com.paintme.models.*;

public interface UserRepository extends CrudRepository<User, Integer>{
}