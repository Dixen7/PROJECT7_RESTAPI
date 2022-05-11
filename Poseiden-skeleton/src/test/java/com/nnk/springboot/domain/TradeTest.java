package com.nnk.springboot.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TradeTest {

    private Trade trade;

    @Test
    public void setAndGetTradeId() {
        trade = new Trade();
        trade.setTradeId(1);
        Assert.assertTrue(trade.getTradeId() == 1);
    }

    @Test
    public void setAndGetAccount() {
        trade = new Trade();
        trade.setAccount("Account");
        Assert.assertTrue(trade.getAccount().equals("Account"));
    }

    @Test
    public void setAndGetType() {
        trade = new Trade();
        trade.setType("Type");
        Assert.assertTrue(trade.getType().equals("Type"));
    }

    @Test
    public void setAndGetBuyQuantity() {
        trade = new Trade();
        trade.setBuyQuantity(10.0);
        Assert.assertTrue(trade.getBuyQuantity() == 10.0);
    }

    @Test
    public void setAndGetSellQuantity() {
        trade = new Trade();
        trade.setSellQuantity(10.0);
        Assert.assertTrue(trade.getSellQuantity() == 10.0);
    }

    @Test
    public void setAndGetBuyPrice() {
        trade = new Trade();
        trade.setBuyPrice(10.0);
        Assert.assertTrue(trade.getBuyPrice() == 10.0);
    }

    @Test
    public void setAndGetSellPrice() {
        trade = new Trade();
        trade.setSellPrice(10.0);
        Assert.assertTrue(trade.getSellPrice() == 10.0);
    }

    @Test
    public void setAndGetBenchmark() {
        trade = new Trade();
        trade.setBenchmark("Benchmark");
        Assert.assertTrue(trade.getBenchmark().equals("Benchmark"));
    }

    @Test
    public void setAndGetSecurity() {
        trade = new Trade();
        trade.setSecurity("Security");
        Assert.assertTrue(trade.getSecurity().equals("Security"));
    }

    @Test
    public void setAndGetStatus() {
        trade = new Trade();
        trade.setStatus("Status");
        Assert.assertTrue(trade.getStatus().equals("Status"));
    }

    @Test
    public void setAndGetTrader() {
        trade = new Trade();
        trade.setTrader("Trader");
        Assert.assertTrue(trade.getTrader().equals("Trader"));
    }

    @Test
    public void setAndGetBook() {
        trade = new Trade();
        trade.setBook("Book");
        Assert.assertTrue(trade.getBook().equals("Book"));
    }

    @Test
    public void setAndGetCreationName() {
        trade = new Trade();
        trade.setCreationName("CreationName");
        Assert.assertTrue(trade.getCreationName().equals("CreationName"));
    }

    @Test
    public void setAndGetRevisionName() {
        trade = new Trade();
        trade.setRevisionName("RevisionName");
        Assert.assertTrue(trade.getRevisionName().equals("RevisionName"));
    }

    @Test
    public void setAndGetDealName() {
        trade = new Trade();
        trade.setDealName("DealName");
        Assert.assertTrue(trade.getDealName().equals("DealName"));
    }

    @Test
    public void setAndGetDealType() {
        trade = new Trade();
        trade.setDealType("DealType");
        Assert.assertTrue(trade.getDealType().equals("DealType"));
    }

    @Test
    public void setAndGetSourceListId() {
        trade = new Trade();
        trade.setSourceListId("SourceListId");
        Assert.assertTrue(trade.getSourceListId().equals("SourceListId"));
    }

    @Test
    public void setAndGetSide() {
        trade = new Trade();
        trade.setSide("Side");
        Assert.assertTrue(trade.getSide().equals("Side"));
    }
}