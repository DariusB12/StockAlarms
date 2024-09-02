package org.example.stockalarms.model.repo;

import org.example.stockalarms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
//    UserEntity findUserByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}