package com.revature.fauxrex.service;

import com.revature.fauxrex.model.Account;
import com.revature.fauxrex.model.AuthenticationResponse;
import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.model.AuthenticationRequest;
import com.revature.fauxrex.repository.TraderRepository;
import com.revature.fauxrex.service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TraderService {

    @Autowired
    private final TraderRepository traderRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TraderDetailsService traderDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    public Trader getTraderByUsername(String username){
        return traderRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    public Trader getTraderByTraderId(Integer id) {
        return traderRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest authReq) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Invalid Credentials", e);
        }
        final UserDetails userDetails = traderDetailsService
                .loadUserByUsername(authReq.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    public Trader saveNewTrader(Trader trader) {
        trader.setPassword(passwordEncoder.encode(trader.getPassword()));

        Account acc = new Account();
        acc.setUsd(100000d);
        acc.setEur(0d);
        acc.setGbp(0d);
        acc.setNzd(0d);
        trader.setAccount(acc);
        System.out.println(trader.getPassword());
        return traderRepository.save(trader);
    }

    public Trader saveTrader(Trader trader) {
        return traderRepository.save(trader);
    }
}
