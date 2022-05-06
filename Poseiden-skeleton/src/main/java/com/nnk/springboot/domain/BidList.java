package com.nnk.springboot.domain;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer BidListId;

    @Size(max = 30)
    @NotBlank(message = "Account is mandatory")
    String account;

    @Size(max = 30)
    @NotBlank(message = "Type is mandatory")
    String type;

    @PositiveOrZero
    Double bidQuantity;

    @PositiveOrZero
    Double askQuantity;

    @PositiveOrZero
    Double bid;

    @PositiveOrZero
    Double ask;

    @Size(max = 125)
    String benchmark;

    @CreationTimestamp
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate bidListDate;

    @Size(max = 125)
    String commentary;

    @Size(max = 125)
    String security;

    @Size(max = 10)
    String status;

    @Size(max = 125)
    String trader;

    @Size(max = 125)
    String book;

    @Size(max = 125)
    String creationName;

    @CreationTimestamp
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate creationDate;

    @Size(max = 125)
    String revisionName;


    @UpdateTimestamp
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate revisionDate;

    @Size(max = 125)
    String dealName;

    @Size(max = 125)
    String dealType;

    @Size(max = 125)
    String sourceListId;

    @Size(max = 125)
    String side;

    public BidList() {
    }

    public BidList(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}