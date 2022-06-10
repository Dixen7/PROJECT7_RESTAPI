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

import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.Rating;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "clement", authorities = {"USER"})
public class RatingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    private List<Rating> listRatingTest;
    private Rating ratingValidTest;

    @BeforeEach
    public void setup() {
        Rating ratingTest = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        ratingValidTest = new Rating("Moodys Rating validTest", "Sand PRating validTest", "Fitch Rating validTest", 20);;
        listRatingTest = new ArrayList<Rating>();
        listRatingTest.add(ratingTest);
    }

    @Test
    public void testHome() throws Exception{
        when(ratingService.getAllRating()).thenReturn(listRatingTest);
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("ratingList"));
    }

    @Test
    public void testAddRatingForm() throws Exception{
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    public void testValidate() throws Exception{
        when(ratingService.saveRating(ratingValidTest)).thenReturn(ratingValidTest);
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "Moodys Rating validTest")
                        .param("sandPRating", "Sand PRating validTest")
                        .param("fitchRating", "Fitch Rating validTest")
                        .param("orderNumber", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    public void testValidate_Negative() throws Exception{
        when(ratingService.saveRating(ratingValidTest)).thenReturn(ratingValidTest);
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "Moodys Rating validTest")
                        .param("sandPRating", "")
                        .param("fitchRating", "Fitch Rating validTest")
                        .param("orderNumber", "20"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"))
                .andExpect(model().attributeHasFieldErrors("rating", "sandPRating"));
    }

    @Test
    public void testShowUpdateForm() throws Exception{
        when(ratingService.getRatingById(1)).thenReturn(ratingValidTest);
        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    public void testUpdate() throws Exception{
        when(ratingService.saveRating(ratingValidTest)).thenReturn(ratingValidTest);
        mockMvc.perform(post("/rating/update/{id}", "2")
                        .param("moodysRating", "Moodys Rating updateTest")
                        .param("sandPRating", "Sand PRating updateTest")
                        .param("fitchRating", "Fitch Rating updateTest")
                        .param("orderNumber", "30"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    public void testUpdate_Negative() throws Exception{
        when(ratingService.saveRating(ratingValidTest)).thenReturn(ratingValidTest);
        mockMvc.perform(post("/rating/update/{id}", "2")
                        .param("moodysRating", "Moodys Rating updateTest")
                        .param("sandPRating", "Sand PRating updateTest")
                        .param("fitchRating", "Fitch Rating updateTest")
                        .param("orderNumber", "thirty"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeHasFieldErrors("rating", "orderNumber"));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(get("/rating/delete/{id}", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }
}