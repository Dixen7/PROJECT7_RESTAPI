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

import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.RuleName;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("USER")
public class RuleNameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RuleNameService ruleNameService;

    private List<RuleName> listRuleTest;
    private RuleName ruleValidTest;

    @BeforeEach
    public void setup() {
        RuleName ruleTest = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleValidTest = new RuleName("Rule Name validTest", "Description validTest", "Json validTest", "Template validTest", "SQL validTest", "SQL Part validTest");
        listRuleTest = new ArrayList<RuleName>();
        listRuleTest.add(ruleTest);
    }

    @Test
    public void testHome() throws Exception{
        when(ruleNameService.getAllRuleName()).thenReturn(listRuleTest);
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("ruleNameList"));
    }

    @Test
    public void testAddRuleForm() throws Exception{
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    public void testValidate() throws Exception{
        when(ruleNameService.saveRuleName(ruleValidTest)).thenReturn(ruleValidTest);
        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "Rule Name validTest")
                        .param("description", "Description validTest")
                        .param("json", "Json validTest")
                        .param("template", "Template validTest")
                        .param("sqlStr", "SQL validTest")
                        .param("sqlPart", "SQL Part validTest"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    public void testValidate_Negative() throws Exception{
        when(ruleNameService.saveRuleName(ruleValidTest)).thenReturn(ruleValidTest);
        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "Rule Name validTest")
                        .param("description", "")
                        .param("json", "Json validTest")
                        .param("template", "Template validTest")
                        .param("sqlStr", "SQL validTest")
                        .param("sqlPart", "SQL Part validTest"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"))
                .andExpect(model().attributeHasFieldErrors("ruleName", "description"));
    }

    @Test
    public void testShowUpdateForm() throws Exception{
        when(ruleNameService.getRuleNameById(1)).thenReturn(ruleValidTest);
        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"));
    }

    @Test
    public void testUpdate() throws Exception{
        when(ruleNameService.saveRuleName(ruleValidTest)).thenReturn(ruleValidTest);
        mockMvc.perform(post("/ruleName/update/{id}", "2")
                        .param("name", "Rule Name validTest")
                        .param("description", "Description validTest")
                        .param("json", "Json validTest")
                        .param("template", "Template validTest")
                        .param("sqlStr", "SQL validTest")
                        .param("sqlPart", "SQL Part validTest"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    public void testUpdate_Negative() throws Exception{
        when(ruleNameService.saveRuleName(ruleValidTest)).thenReturn(ruleValidTest);
        mockMvc.perform(post("/ruleName/update/{id}", "2")
                        .param("name", "Rule Name validTest")
                        .param("description", "Description validTest")
                        .param("json", "Json validTest")
                        .param("template", "")
                        .param("sqlStr", "SQL validTest")
                        .param("sqlPart", "SQL Part validTest"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeHasFieldErrors("ruleName", "template"));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(get("/ruleName/delete/{id}", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }
}