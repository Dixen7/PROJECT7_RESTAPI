package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@DynamicUpdate
@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer bidListId;
    @NotBlank(message = "Account is mandatory")
    private String account;
    @NotBlank(message = "Type is mandatory")
    private String type;
    @NotNull
    @Digits(fraction = 2, integer = 10)
    @Positive(message="Bid Quantity must be positive")
    private Double bidQuantity;
    private Double askQuantity;
    private Double bid;
    private Double ask;
    private String benchmark;
    private Timestamp bidListDate;
    private String commentary;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;
    private Timestamp creationDate;
    private String revisionName;
    private Timestamp revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;


    public BidList() {
    }

    public BidList(@NotBlank(message = "Account is mandatory") String account,
                   @NotBlank(message = "Type is mandatory") String type,
                   @NotBlank Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

}