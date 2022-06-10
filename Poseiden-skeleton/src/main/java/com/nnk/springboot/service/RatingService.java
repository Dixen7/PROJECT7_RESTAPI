package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.Rating;

public interface RatingService {

    List<Rating> getAllRating();

    Rating saveRating(Rating rating);

    Rating updateRating(Integer id, Rating rating);

    Rating getRatingById(Integer id);

    boolean deleteRatingById(Integer id);

}