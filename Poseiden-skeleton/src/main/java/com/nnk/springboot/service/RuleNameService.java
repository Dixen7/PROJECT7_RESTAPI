package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

public interface RuleNameService {

    List<RuleName> getAllRuleName();

    RuleName saveRuleName(RuleName ruleName);

    RuleName updateRuleName(Integer id, RuleName ruleName);

    RuleName getRuleNameById(Integer id);

    boolean deleteRuleNameById(Integer id);

}