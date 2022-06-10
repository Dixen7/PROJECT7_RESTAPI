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

import com.nnk.springboot.service.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.CurvePoint;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("USER")
public class CurveControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;

    private List<CurvePoint> listCurveTest;
    private CurvePoint curveValidTest;

    @BeforeEach
    public void setup() {
        CurvePoint curveTest = new CurvePoint(10, 10d, 30d);
        curveValidTest = new CurvePoint(20, 20d, 40d);
        listCurveTest = new ArrayList<CurvePoint>();
        listCurveTest.add(curveTest);
    }

    @Test
    public void testHome() throws Exception{
        when(curvePointService.getAllCurvePoint()).thenReturn(listCurveTest);
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePointList"));
    }

    @Test
    public void testAddBidForm() throws Exception{
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    public void testValidate() throws Exception{
        when(curvePointService.saveCurvePoint(curveValidTest)).thenReturn(curveValidTest);
        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "20")
                        .param("term", "20")
                        .param("value", "40"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    public void testValidate_Negative() throws Exception{
        when(curvePointService.saveCurvePoint(curveValidTest)).thenReturn(curveValidTest);
        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "20")
                        .param("term", "twenty")
                        .param("value", "40"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"))
                .andExpect(model().attributeHasFieldErrors("curvePoint", "term"));
    }

    @Test
    public void testShowUpdateForm() throws Exception{
        when(curvePointService.getCurvePointById(1)).thenReturn(curveValidTest);
        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test
    public void testUpdate() throws Exception{
        when(curvePointService.saveCurvePoint(curveValidTest)).thenReturn(curveValidTest);
        mockMvc.perform(post("/curvePoint/update/{id}", "2")
                        .param("curveId", "30")
                        .param("term", "30")
                        .param("value", "50"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    public void testUpdate_Negative() throws Exception{
        when(curvePointService.saveCurvePoint(curveValidTest)).thenReturn(curveValidTest);
        mockMvc.perform(post("/curvePoint/update/{id}", "2")
                        .param("curveId", "30")
                        .param("term", "30")
                        .param("value", "-40"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeHasFieldErrors("curvePoint", "value"));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(get("/curvePoint/delete/{id}", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }
}