package com.revature.fauxrex.service;


import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.repository.TraderRepository;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class TraderServiceTest {
    static Trader tedTest = new Trader(1,
            "ted_test", "password",
            "Ted", "Test");
    @Autowired
    private TraderService service;

    @MockBean
    private TraderRepository repository;


    @Test
    public void getAllTradersTest() {
        when(repository.findAll()).thenReturn(Stream
                .of(tedTest)
                .collect(Collectors.toList()));
        assertEquals(1, service.getAllTraders().size());
    }

    @Test
    public void getTraderByUsernameTest() {
        when(repository.findByUsername(tedTest.getUsername())).thenReturn(java.util.Optional
                .of(tedTest));
        assertEquals(1, service.getTraderByUsername(tedTest.getUsername())
                .getId());
    }

    @Test
    public void getTraderByTraderIDTest() {
        when(repository.findById(1)).thenReturn(java.util.Optional
                .of(tedTest));
        assertEquals(1, service.getTraderByTraderId(1)
                .getId());
    }

    @Test
    public void saveTraderTest() {
        when(repository.save(tedTest)).thenReturn(tedTest);
        assertEquals(1, service.saveTrader(tedTest).getId());
    }

//    @Test
//    public void deleteTraderTest(){
//        when(repository.findById(tedTest.getId()))
//                .thenReturn(java.util.Optional
//                .of(tedTest));
//
//        MockMvc.perform(MockMvcRequestBuilders.delete("/trader", 1))
//                .andExpect(status().isOk());
//    }


}
