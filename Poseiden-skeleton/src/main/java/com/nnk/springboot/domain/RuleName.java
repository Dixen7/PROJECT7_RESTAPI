package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@DynamicUpdate
@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotBlank(message = "Json is mandatory")
    private String json;
    @NotBlank(message = "Template is mandatory")
    private String template;
    @NotBlank(message = "SqlStr is mandatory")
    private String sqlStr;
    @NotBlank(message = "SqlPart is mandatory")
    private String sqlPart;


    public RuleName() {
    }

    public RuleName(@NotBlank String name, @NotBlank String description,
                    @NotBlank String json, @NotBlank String template, @NotBlank String sqlStr,
                    @NotBlank String sqlPart) {
        super();
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }
}