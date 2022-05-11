package com.nnk.springboot.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BidTest {

    private BidList bidList;

    @Test
    public void setAndGetBidListId() {
        bidList = new BidList();
        bidList.setBidListId(1);
        Assert.assertTrue(bidList.getBidListId() == 1);
    }

    @Test
    public void setAndGetAccount() {
        bidList = new BidList();
        bidList.setAccount("Account");
        Assert.assertTrue(bidList.getAccount().equals("Account"));
    }

    @Test
    public void setAndGetType() {
        bidList = new BidList();
        bidList.setType("Type");
        Assert.assertTrue(bidList.getType().equals("Type"));
    }

    @Test
    public void setAndGetBidQuantity() {
        bidList = new BidList();
        bidList.setBidQuantity(10.0);
        Assert.assertTrue(bidList.getBidQuantity() == 10.0);
    }

    @Test
    public void setAndGetAskQuantity() {
        bidList = new BidList();
        bidList.setAskQuantity(10.0);
        Assert.assertTrue(bidList.getAskQuantity() == 10.0);
    }

    @Test
    public void setAndGetBid() {
        bidList = new BidList();
        bidList.setBid(10.0);
        Assert.assertTrue(bidList.getBid() == 10.0);
    }

    @Test
    public void setAndGetAsk() {
        bidList = new BidList();
        bidList.setAsk(10.0);
        Assert.assertTrue(bidList.getAsk() == 10.0);
    }

    @Test
    public void setAndGetBenchmark() {
        bidList = new BidList();
        bidList.setBenchmark("Benchmark");
        Assert.assertTrue(bidList.getBenchmark().equals("Benchmark"));
    }

    @Test
    public void setAndGetCommentary() {
        bidList = new BidList();
        bidList.setCommentary("Commentary");
        Assert.assertTrue(bidList.getCommentary().equals("Commentary"));
    }

    @Test
    public void setAndGetSecurity() {
        bidList = new BidList();
        bidList.setSecurity("Security");
        Assert.assertTrue(bidList.getSecurity().equals("Security"));
    }

    @Test
    public void setAndGetStatus() {
        bidList = new BidList();
        bidList.setStatus("Status");
        Assert.assertTrue(bidList.getStatus().equals("Status"));
    }

    @Test
    public void setAndGetTrader() {
        bidList = new BidList();
        bidList.setTrader("Trader");
        Assert.assertTrue(bidList.getTrader().equals("Trader"));
    }

    @Test
    public void setAndGetBook() {
        bidList = new BidList();
        bidList.setBook("Book");
        Assert.assertTrue(bidList.getBook().equals("Book"));
    }

    @Test
    public void setAndGetCreationName() {
        bidList = new BidList();
        bidList.setCreationName("CreationName");
        Assert.assertTrue(bidList.getCreationName().equals("CreationName"));
    }

    @Test
    public void setAndGetRevisionName() {
        bidList = new BidList();
        bidList.setRevisionName("RevisionName");
        Assert.assertTrue(bidList.getRevisionName().equals("RevisionName"));
    }

    @Test
    public void setAndGetDealName() {
        bidList = new BidList();
        bidList.setDealName("DealName");
        Assert.assertTrue(bidList.getDealName().equals("DealName"));
    }

    @Test
    public void setAndGetDealType() {
        bidList = new BidList();
        bidList.setDealType("DealType");
        Assert.assertTrue(bidList.getDealType().equals("DealType"));
    }

    @Test
    public void setAndGetSourceListId() {
        bidList = new BidList();
        bidList.setSourceListId("SourceListId");
        Assert.assertTrue(bidList.getSourceListId().equals("SourceListId"));
    }

    @Test
    public void setAndGetSide() {
        bidList = new BidList();
        bidList.setSide("Side");
        Assert.assertTrue(bidList.getSide().equals("Side"));
    }
}