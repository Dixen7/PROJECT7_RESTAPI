package com.nnk.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import com.nnk.springboot.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.Trade;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("USER")
public class TradeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    private List<Trade> listTradeTest;
    private Trade tradeValidTest;

    @BeforeEach
    public void setup() {
        Trade tradeTest = new Trade("Trade Account", "Type", 10d);
        tradeValidTest = new Trade("Trade Account validTest", "Type validTest", 20d);
        listTradeTest = new ArrayList<Trade>();
        listTradeTest.add(tradeTest);
    }

    @Test
    public void testHome() throws Exception{
        when(tradeService.getAllTrade()).thenReturn(listTradeTest);
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"))
                .andExpect(model().attributeExists("tradeList"));
    }

    @Test
    public void testAddUser() throws Exception{
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    public void testValidate() throws Exception{
        when(tradeService.saveTrade(tradeValidTest)).thenReturn(tradeValidTest);
        mockMvc.perform(post("/trade/validate")
                        .param("account", "Trade Account validTest")
                        .param("type", "Type validTest")
                        .param("buyQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    public void testValidate_Negative() throws Exception{
        when(tradeService.saveTrade(tradeValidTest)).thenReturn(tradeValidTest);
        mockMvc.perform(post("/trade/validate")
                        .param("account", "Trade Account validTest")
                        .param("type", "Type validTest")
                        .param("buyQuantity", "-20"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"))
                .andExpect(model().attributeHasFieldErrors("trade", "buyQuantity"));
    }

    @Test
    public void testShowUpdateForm() throws Exception{
        when(tradeService.getTradeById(1)).thenReturn(tradeValidTest);
        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"))
                .andExpect(model().attributeExists("trade"));
    }

    @Test
    public void testUpdate() throws Exception{
        when(tradeService.saveTrade(tradeValidTest)).thenReturn(tradeValidTest);
        mockMvc.perform(post("/trade/update/{id}", "2")
                        .param("account", "Trade Account updateTest")
                        .param("type", "Type updateTest")
                        .param("buyQuantity", "30"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    public void testUpdate_Negative() throws Exception{
        when(tradeService.saveTrade(tradeValidTest)).thenReturn(tradeValidTest);
        mockMvc.perform(post("/trade/update/{id}", "2")
                        .param("account", "Trade Account updateTest")
                        .param("type", "")
                        .param("buyQuantity", "30"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"))
                .andExpect(model().attributeHasFieldErrors("trade", "type"));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(get("/trade/delete/{id}", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }
}