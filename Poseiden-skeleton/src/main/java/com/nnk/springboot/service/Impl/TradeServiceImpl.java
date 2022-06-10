package com.nnk.springboot.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.nnk.springboot.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeServiceImpl implements TradeService {

    private static Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);

    private TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public List<Trade> getAllTrade(){
        logger.info("Getting a list of all Trade");
        return tradeRepository.findAll();
    }

    @Override
    @Transactional
    public Trade saveTrade(Trade trade) {
        if(Objects.isNull(trade)) {
            logger.error("New Trade must not be null");
            return null;
        }
        logger.info("Creating a New Trade");
        return tradeRepository.save(trade);
    }

    @Override
    @Transactional
    public Trade updateTrade(Integer id, Trade trade) {
        Optional<Trade> optTrade = tradeRepository.findById(id);
        if(optTrade.isPresent()) {
            Trade updatingTrade = optTrade.get();
            updatingTrade.setAccount(trade.getAccount());
            updatingTrade.setType(trade.getType());
            updatingTrade.setBuyQuantity(trade.getBuyQuantity());
            logger.info("Trade " + id + " updated");
            return tradeRepository.save(updatingTrade);
        } else {
            logger.error("Trade with id: " + id + " not found nor updated");
            return null;
        }
    }

    @Override
    public Trade getTradeById(Integer id) {
        Optional<Trade> optTrade = tradeRepository.findById(id);
        if(optTrade.isPresent()) {
            logger.info("Trade with id " + id + " found");
            return optTrade.get();
        } else {
            logger.error("Trade with id: " + id + " not found");
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        Optional<Trade> optTrade = tradeRepository.findById(id);
        if(optTrade.isPresent()) {
            tradeRepository.deleteById(id);
            logger.info("Trade with id " + id + " deleted");
            return true;
        } else {
            logger.error("Trade with id: " + id + " not found nor deleted");
            return false;
        }
    }
}