package com.revature.fauxrex.service;

import com.revature.fauxrex.model.Trade;
import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.repository.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class TradeServiceTest {
    static Trader tedTest = new Trader(1,
            "ted_test","password",
            "Ted","Test");


    @Autowired
    private TradeService service;

    @MockBean
    private TradeRepository repository;

    @Test
    void saveTrade() {

    }

    @Test
    void getTradeById() {
    }

    @Test
    void getTradeByIdAndCurrencyPairId() {
    }

    @Test
    void getTradeByCurrencyPairId() {
    }

    @Test
    void getCurrencyPairById() {
    }
}