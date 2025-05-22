package com.example.mutualfundportfolio.service;

import com.example.mutualfundportfolio.entity.FundPosition;
import com.example.mutualfundportfolio.repository.FundPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {
    @Autowired
    private FundPositionRepository repo;

    public List<FundPosition> getPortfolio(Long userId, String currency) {
        List<FundPosition> positions = repo.findByUserId(userId);
        // Currency conversion logic can go here
        return positions;
    }
}
