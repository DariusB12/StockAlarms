package org.example.stockalarms.model.repo;

import org.example.stockalarms.model.Alarm;
import org.example.stockalarms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlarmRepo extends JpaRepository<Alarm,Integer> {
    Optional<Alarm> findBySymbol(String symbol);
    List<Alarm> findByUser(UserEntity user);
    List<Alarm> findAllByActiveIsTrue();
}
