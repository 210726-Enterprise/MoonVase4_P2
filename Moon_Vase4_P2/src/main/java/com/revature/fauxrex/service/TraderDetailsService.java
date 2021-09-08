package com.revature.fauxrex.service;

import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TraderDetailsService implements UserDetailsService {

    @Autowired
    private final TraderRepository traderRepository;

    @Autowired
    public TraderDetailsService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Trader trader = traderRepository.findByUsername(s).orElseThrow(RuntimeException::new);
        String username = trader.getUsername();
        String password = trader.getPassword();
        return new User(username, password, new ArrayList<>()); // ArrayList because we aren't dealing with Authorities
    }

}
