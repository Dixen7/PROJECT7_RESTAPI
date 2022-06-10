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

import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import com.nnk.springboot.domain.BidList;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("USER")
public class BidListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    private List<BidList> listBidTest;
    private BidList bidValidTest;

    @BeforeEach
    public void setup() {
        BidList bidTest = new BidList("Account Test", "Type Test", 10d);
        bidValidTest = new BidList("Account Test Valid", "Type Test Valid", 20d);
        listBidTest = new ArrayList<BidList>();
        listBidTest.add(bidTest);
    }

    @Test
    public void testHome() throws Exception{
        when(bidListService.getAllBidList()).thenReturn(listBidTest);
        mockMvc.perform(get("/bidList/list")
                        .param("userInfo", "name"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("listBid"));
    }

    @Test
    public void testAddBidForm() throws Exception{
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    public void testValidate() throws Exception{
        when(bidListService.saveBidList(bidValidTest)).thenReturn(bidValidTest);
        mockMvc.perform(post("/bidList/validate")
                        .param("account", "Account Test Valid")
                        .param("type", "Type Test Valid")
                        .param("bidQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    public void testValidate_Negative() throws Exception{
        when(bidListService.saveBidList(bidValidTest)).thenReturn(bidValidTest);
        mockMvc.perform(post("/bidList/validate")
                        .param("account", "Account Test Valid")
                        .param("type", "Type Test Valid")
                        .param("bidQuantity", "-20"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"))
                .andExpect(model().attributeHasFieldErrors("bidList", "bidQuantity"));
    }

    @Test
    public void testShowUpdateForm() throws Exception{
        when(bidListService.getBidListById(1)).thenReturn(bidValidTest);
        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"));
    }

    @Test
    public void testUpdate() throws Exception{
        when(bidListService.saveBidList(bidValidTest)).thenReturn(bidValidTest);
        mockMvc.perform(post("/bidList/update/{id}", "1")
                        .param("account", "Account Test Update")
                        .param("type", "Type Test Update")
                        .param("bidQuantity", "30"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    public void testUpdate_Negative() throws Exception{
        when(bidListService.saveBidList(bidValidTest)).thenReturn(bidValidTest);
        mockMvc.perform(post("/bidList/update/{id}", "1")
                        .param("account", "Account Test Update")
                        .param("type", "")
                        .param("bidQuantity", "30"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeHasFieldErrors("bidList", "type"));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(get("/bidList/delete/{id}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }
}