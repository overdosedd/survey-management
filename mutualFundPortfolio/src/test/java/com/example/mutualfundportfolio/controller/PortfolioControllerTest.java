package com.example.mutualfundportfolio.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.mutualfundportfolio.controller.PortfolioController;
import com.example.mutualfundportfolio.entity.FundPosition;
import com.example.mutualfundportfolio.service.PortfolioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

@WebMvcTest(PortfolioController.class)
@ExtendWith(MockitoExtension.class)
class PortfolioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PortfolioService service;

    @Test
    void testGetPortfolio() throws Exception {
        List<FundPosition> mockPositions = List.of(
                new FundPosition(1L,  "FUND1", new BigDecimal("100.0"), new BigDecimal("2000.00"),"SGD"),
                new FundPosition(2L,  "FUND2", new BigDecimal("50.0"), new BigDecimal("1500.00"),"JPY")
        );

        when(service.getPortfolio(1L, "SGD")).thenReturn(mockPositions);

        mockMvc.perform(get("/portfolio")
                        .param("userId", "1")
                        .param("currency", "SGD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].units").value(100.0))
                .andExpect(jsonPath("$[1].cash_amount").value(1500.00));
    }
}