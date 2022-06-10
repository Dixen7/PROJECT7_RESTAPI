package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.Trade;

public interface TradeService {

    List<Trade> getAllTrade();

    Trade saveTrade(Trade trade);

    Trade updateTrade(Integer id, Trade trade);

    Trade getTradeById(Integer id);

    boolean deleteById(Integer id);

}