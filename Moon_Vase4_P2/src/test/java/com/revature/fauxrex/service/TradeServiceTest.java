package com.revature.fauxrex.service;

import com.revature.fauxrex.model.CurrencyPair;
import com.revature.fauxrex.model.Trade;
import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.repository.TradeRepository;
import com.revature.fauxrex.repository.TraderRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TradeServiceTest {

    static Trader tedTest = new Trader(1, "ted_test","password", "Ted","Test");

    static long now = System.currentTimeMillis();

    static Timestamp sqlTimestamp = new Timestamp(now);


    static CurrencyPair currency = new CurrencyPair(1, "eurusd");

    static Trade tradeTest = new Trade( 1, tedTest , sqlTimestamp , currency, 1.32, 12535.24);






    static String cp1 = "eurusd";
    static String cp2 = "gbpusd";
    static String cp3 = "nzdusd";




    @Autowired
    private TradeService service;

    @MockBean
    private TradeRepository repository;

    @MockBean
    private TraderRepository repo;

    @MockBean
    private TraderService tservice;


    @Test
    void saveTradeTest() throws JSONException, IOException {
        when(tservice.getTraderByTraderId(1)).thenReturn(tedTest);
        when(repo.findById(tedTest.getId())).thenReturn(java.util.Optional.ofNullable(tedTest));
//        when(cpr.getById(cp.getId())).thenReturn(cp);
//        when(qService.getQuote(cp.getCurrencyPair())).thenReturn(q);
        when(repository.save(tradeTest)).thenReturn(tradeTest);
        assertEquals(1,  service.saveTrade(tradeTest).getId());
    }
//
//    @Test
//    void getTradeByIdTest() {
//
//        when(repository.findByTradeId(tedTest.getId())).thenReturn(Stream.of(tradeTest).collect(Collectors.toList()));
//        assertEquals(1,  service.getTradeById(tedTest.getId()).size());
//    }


}