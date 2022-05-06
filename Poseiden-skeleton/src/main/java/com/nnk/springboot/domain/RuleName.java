package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    @Size(max = 125)
    String name;

    @Size(max = 125)
    String description;

    @Size(max = 125)
    String json;

    @Size(max = 512)
    String template;

    @Size(max = 125)
    String sqlStr;

    @Size(max = 125)
    String sqlPart;

    public RuleName() {
    }

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }
}