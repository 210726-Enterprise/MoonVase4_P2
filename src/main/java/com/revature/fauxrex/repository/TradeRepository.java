package com.revature.fauxrex.repository;

import com.revature.fauxrex.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
    List<Trade> findByTraderIdOrderByTimestampDesc(int traderId);
    List<Trade> findByTraderId(int traderId);

}
