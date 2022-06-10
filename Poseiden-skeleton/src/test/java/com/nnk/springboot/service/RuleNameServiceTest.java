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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@SpringBootTest
public class RuleNameServiceTest {

    @Autowired
    private RuleNameService ruleNameService;
    @Autowired
    private RuleNameRepository ruleNameRepository;

    RuleName ruleTest = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
    RuleName ruleDeleteTest = new RuleName("Rule Name delTest", "Description delTest", "Json delTest", "Template delTest", "SQL delTest", "SQL Part delTest");
    RuleName ruleUpdateTest = new RuleName("Rule Name updaTest", "Description updaTest", "Json updaTest", "Template updaTest", "SQL updaTest", "SQL Part updaTest");

    @BeforeEach
    public void setDb() {
        ruleNameRepository.deleteAll();
        ruleNameService.saveRuleName(ruleTest);
        ruleNameService.saveRuleName(ruleDeleteTest);
        ruleNameService.saveRuleName(ruleUpdateTest);
    }

    @Test
    public void testGetAllRuleName() {
        List<RuleName> ruleListTest = ruleNameService.getAllRuleName();
        assertNotNull(ruleListTest);
        assertTrue(ruleListTest.size()>0);
    }

    @Test
    public void testSaveRuleName() {
        RuleName saveRuleTest = new RuleName("Rule Name saveTest", "Description saveTest", "Json saveTest", "Template saveTest", "SQL saveTest", "SQL Part saveTest");
        saveRuleTest = ruleNameService.saveRuleName(saveRuleTest);
        assertNotNull(saveRuleTest);
        assertEquals("Json saveTest",saveRuleTest.getJson());
    }

    @Test
    public void testSaveRuleName_Null() {
        RuleName saveRuleTest = null;
        saveRuleTest = ruleNameService.saveRuleName(saveRuleTest);
        assertNull(saveRuleTest);
    }

    @Test
    public void testUpdateRuleName() {
        Integer ruleUpdateIdTest = ruleUpdateTest.getId();
        RuleName updateRuleTest = new RuleName("Rule Name Testupdt", "Description Testupdt", "Json Testupdt", "Template Testupdt", "SQL Testupdt", "SQL Part Testupdt");
        updateRuleTest = ruleNameService.updateRuleName(ruleUpdateIdTest, updateRuleTest);
        assertNotNull(updateRuleTest);
        assertEquals("Json Testupdt",updateRuleTest.getJson());
    }

    @Test
    public void testUpdateRuleName_Null() {
        RuleName updateRuleTest = new RuleName("Rule Name Testupdt", "Description Testupdt", "Json Testupdt", "Template Testupdt", "SQL Testupdt", "SQL Part Testupdt");
        updateRuleTest = ruleNameService.updateRuleName(999999999, updateRuleTest);
        assertNull(updateRuleTest);
    }

    @Test
    public void testGetRuleNameById() {
        Integer ruleIdTest = ruleTest.getId();
        RuleName ruleByIdTest = ruleNameService.getRuleNameById(ruleIdTest);
        assertNotNull(ruleByIdTest);
        assertEquals("Template", ruleByIdTest.getTemplate());
    }

    @Test
    public void testGetRuleNameById_Null() {
        RuleName ruleByIdTest = ruleNameService.getRuleNameById(999999999);
        assertNull(ruleByIdTest);
    }

    @Test
    public void testDeleteRuleNameById() {
        Integer ruleDeleteIdTest = ruleDeleteTest.getId();
        ruleNameService.deleteRuleNameById(ruleDeleteIdTest);
        assertNull(ruleNameService.getRuleNameById(ruleDeleteIdTest));
    }

    @Test
    public void testDeleteRuleNameById_False() {
        assertFalse(ruleNameService.deleteRuleNameById(999999999));
    }
}