package com.revature.fauxrex.repository;

import com.revature.fauxrex.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Integer> {
    Optional<Trader> findByUsername(String username);
}
