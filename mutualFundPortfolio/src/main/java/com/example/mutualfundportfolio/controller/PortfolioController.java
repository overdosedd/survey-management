package com.example.mutualfundportfolio.controller;

import com.example.mutualfundportfolio.entity.FundPosition;
import com.example.mutualfundportfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping
    public List<FundPosition> getPortfolio(@RequestParam Long userId, @RequestParam String currency) {
        return portfolioService.getPortfolio(userId, currency);
    }
}