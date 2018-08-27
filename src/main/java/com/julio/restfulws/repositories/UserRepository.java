package com.julio.restfulws.repositories;

import com.julio.restfulws.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
