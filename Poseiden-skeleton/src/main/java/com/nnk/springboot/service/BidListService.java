package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;

import java.util.List;
import java.util.Optional;

public interface BidListService {

    List<BidList> getAllBidList();

    BidList saveBidList(BidList bidList);

    BidList updateBidList(Integer id, BidList bidList);

    BidList getBidListById(Integer id);

    boolean deleteBidListById(Integer id);
}
