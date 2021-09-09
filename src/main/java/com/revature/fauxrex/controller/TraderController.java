package com.revature.fauxrex.controller;

import com.revature.fauxrex.model.Trader;
import com.revature.fauxrex.model.AuthenticationRequest;
import com.revature.fauxrex.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Controller
@RequestMapping("/trader")

public class TraderController {
    @Autowired
    private final TraderService traderService;

    @Autowired
    public TraderController(TraderService traderService) {this.traderService = traderService;}

    @PostMapping("/register")
    public @ResponseBody
    Trader createTrader(@RequestBody Trader t) {
        return traderService.saveNewTrader(t);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authReq) throws Exception {
        return traderService.authenticate(authReq);
    }

    @GetMapping("/username")
    public @ResponseBody Trader getTraderByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return traderService.getTraderByUsername(username);
    }
}
