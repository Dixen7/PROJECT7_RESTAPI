package com.nnk.springboot.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RatingTest {

    private Rating rating;

    @Test
    public void setAndGetId() {
        rating = new Rating();
        rating.setId(1);
        Assert.assertTrue(rating.getId() == 1);
    }

    @Test
    public void setAndGetMoodysRating() {
        rating = new Rating();
        rating.setMoodysRating("MoodysRating");
        Assert.assertTrue(rating.getMoodysRating().equals("MoodysRating"));
    }

    @Test
    public void setAndGetSandPRating() {
        rating = new Rating();
        rating.setSandPRating("SandPRating");
        Assert.assertTrue(rating.getSandPRating().equals("SandPRating"));
    }

    @Test
    public void setAndGetFitchRating() {
        rating = new Rating();
        rating.setFitchRating("FitchRating");
        Assert.assertTrue(rating.getFitchRating().equals("FitchRating"));
    }

    @Test
    public void setAndGetOrderNumber() {
        rating = new Rating();
        rating.setOrderNumber(1);
        Assert.assertTrue(rating.getOrderNumber() == 1);
    }
}
