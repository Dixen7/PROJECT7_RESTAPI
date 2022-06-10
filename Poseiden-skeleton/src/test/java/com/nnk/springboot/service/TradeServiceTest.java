package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@SpringBootTest
public class TradeServiceTest {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private TradeRepository tradeRepository;

    Trade tradeTest = new Trade("Trade Account", "Type", 10d);
    Trade tradeDeleteTest = new Trade("Trade Account delTest", "Type delTest", 20d);
    Trade tradeUpdateTest = new Trade("Trade Account updaTest", "Type updaTest", 50d);

    @BeforeEach
    public void setDb() {
        tradeRepository.deleteAll();
        tradeService.saveTrade(tradeTest);
        tradeService.saveTrade(tradeDeleteTest);
        tradeService.saveTrade(tradeUpdateTest);
    }

    @Test
    public void testGetAllTrade() {
        List<Trade> tradeListTest = tradeService.getAllTrade();
        assertNotNull(tradeListTest);
        assertTrue(tradeListTest.size()>0);
    }

    @Test
    public void testSaveTrade() {
        Trade saveTradeTest = new Trade("Trade Account saveTest", "Type saveTest", 30d);
        saveTradeTest = tradeService.saveTrade(saveTradeTest);
        assertNotNull(saveTradeTest);
        assertEquals("Trade Account saveTest",saveTradeTest.getAccount());
    }

    @Test
    public void testSaveTrade_Null() {
        Trade saveTradeTest = null;
        saveTradeTest = tradeService.saveTrade(saveTradeTest);
        assertNull(saveTradeTest);
    }

    @Test
    public void testUpdateTrade() {
        Integer tradeUpdateIdTest = tradeUpdateTest.getTradeId();
        Trade updateTradeTest = new Trade("Trade Account Testupdt", "Type Testupdt", 40d);
        updateTradeTest = tradeService.updateTrade(tradeUpdateIdTest, updateTradeTest);
        assertNotNull(updateTradeTest);
        assertEquals("Trade Account Testupdt",updateTradeTest.getAccount());
    }

    @Test
    public void testUpdateTrade_Null() {
        Trade updateTradeTest = new Trade("Trade Account Testupdt", "Type Testupdt", 40d);
        updateTradeTest = tradeService.updateTrade(999999999, updateTradeTest);
        assertNull(updateTradeTest);
    }

    @Test
    public void testGetTradeById() {
        Integer tradeIdTest = tradeTest.getTradeId();
        Trade tradeByIdTest = tradeService.getTradeById(tradeIdTest);
        assertNotNull(tradeByIdTest);
        assertEquals("Type", tradeByIdTest.getType());
    }

    @Test
    public void testGetTradeById_Null() {
        Trade tradeByIdTest = tradeService.getTradeById(999999999);
        assertNull(tradeByIdTest);
    }

    @Test
    public void testDeleteById() {
        Integer tradeDeleteIdTest = tradeDeleteTest.getTradeId();
        tradeService.deleteById(tradeDeleteIdTest);
        assertNull(tradeService.getTradeById(tradeDeleteIdTest));
    }

    @Test
    public void testDeleteById_False() {
        assertFalse(tradeService.deleteById(999999999));
    }
}