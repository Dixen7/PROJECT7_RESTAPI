package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer tradeId;

    @Size(max = 30)
    @NotBlank(message = "Account is mandatory")
    String account;

    @Size(max = 30)
    @NotBlank(message = "Type is mandatory")
    String type;

    @PositiveOrZero
    Double buyQuantity;

    @PositiveOrZero
    Double sellQuantity;

    @PositiveOrZero
    Double buyPrice;

    @PositiveOrZero
    Double sellPrice;

    @Size(max = 125)
    String benchmark;

    @CreationTimestamp
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate tradeDate;

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

    public Trade() {
    }

    public Trade(String account, String type, double buyQuantity) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(tradeId, trade.tradeId) && Objects.equals(account, trade.account) && Objects.equals(type, trade.type) && Objects.equals(buyQuantity, trade.buyQuantity) && Objects.equals(sellQuantity, trade.sellQuantity) && Objects.equals(buyPrice, trade.buyPrice) && Objects.equals(sellPrice, trade.sellPrice) && Objects.equals(benchmark, trade.benchmark) && Objects.equals(tradeDate, trade.tradeDate) && Objects.equals(security, trade.security) && Objects.equals(status, trade.status) && Objects.equals(trader, trade.trader) && Objects.equals(book, trade.book) && Objects.equals(creationName, trade.creationName) && Objects.equals(creationDate, trade.creationDate) && Objects.equals(revisionName, trade.revisionName) && Objects.equals(revisionDate, trade.revisionDate) && Objects.equals(dealName, trade.dealName) && Objects.equals(dealType, trade.dealType) && Objects.equals(sourceListId, trade.sourceListId) && Objects.equals(side, trade.side);
    }

}