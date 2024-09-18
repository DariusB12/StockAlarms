package org.example.stockalarms.model.repo;

import org.example.stockalarms.model.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SymbolRepo extends JpaRepository<Symbol,Integer> {
}
