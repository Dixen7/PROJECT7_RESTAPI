package com.nnk.springboot.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RuleNameTest {

    private RuleName ruleName;

    @Test
    public void setAndGetId() {
        ruleName = new RuleName();
        ruleName.setId(1);
        Assert.assertTrue(ruleName.getId() == 1);
    }

    @Test
    public void setAndGetName() {
        ruleName = new RuleName();
        ruleName.setName("Name");
        Assert.assertTrue(ruleName.getName().equals("Name"));
    }

    @Test
    public void setAndGetDescription() {
        ruleName = new RuleName();
        ruleName.setDescription("Description");
        Assert.assertTrue(ruleName.getDescription().equals("Description"));
    }

    @Test
    public void setAndGetJson() {
        ruleName = new RuleName();
        ruleName.setJson("Json");
        Assert.assertTrue(ruleName.getJson().equals("Json"));
    }

    @Test
    public void setAndGetTemplate() {
        ruleName = new RuleName();
        ruleName.setTemplate("Template");
        Assert.assertTrue(ruleName.getTemplate().equals("Template"));
    }

    @Test
    public void setAndGetSqlStr() {
        ruleName = new RuleName();
        ruleName.setSqlStr("SqlStr");
        Assert.assertTrue(ruleName.getSqlStr().equals("SqlStr"));
    }

    @Test
    public void setAndGetSqlPart() {
        ruleName = new RuleName();
        ruleName.setSqlPart("SqlPart");
        Assert.assertTrue(ruleName.getSqlPart().equals("SqlPart"));
    }
}