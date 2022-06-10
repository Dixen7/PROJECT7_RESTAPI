package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@SpringBootTest
public class RatingServiceTest {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private RatingRepository ratingRepository;

    Rating ratingTest = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
    Rating ratingDeleteTest = new Rating("Moodys Rating del", "Sand PRating del", "Fitch Rating del", 20);
    Rating ratingUpdateTest = new Rating("Moodys Rating update", "Sand PRating update", "Fitch Rating update", 50);

    @BeforeEach
    public void setDb() {
        ratingRepository.deleteAll();
        ratingService.saveRating(ratingTest);
        ratingService.saveRating(ratingDeleteTest);
        ratingService.saveRating(ratingUpdateTest);
    }

    @Test
    public void testGetAllRating() {
        List<Rating> ratingListTest = ratingService.getAllRating();
        assertNotNull(ratingListTest);
        assertTrue(ratingListTest.size()>0);
    }

    @Test
    public void testSaveRating() {
        Rating saveRatingTest = new Rating("Moodys Rating savetest", "Sand PRating savetest", "Fitch Rating savetest", 30);
        saveRatingTest = ratingService.saveRating(saveRatingTest);
        assertNotNull(saveRatingTest);
        assertEquals("Moodys Rating savetest",saveRatingTest.getMoodysRating());
    }

    @Test
    public void testSaveRating_Null() {
        Rating saveRatingTest = null;
        saveRatingTest = ratingService.saveRating(saveRatingTest);
        assertNull(saveRatingTest);
    }

    @Test
    public void testUpdateRating() {
        Integer ratingUpdateIdTest = ratingUpdateTest.getId();
        Rating updateRatingTest = new Rating("Moodys Rating updatetest", "Sand PRating updatetest", "Fitch Rating updatetest", 40);
        updateRatingTest = ratingService.updateRating(ratingUpdateIdTest, updateRatingTest);
        assertNotNull(updateRatingTest);
        assertEquals("Moodys Rating updatetest",updateRatingTest.getMoodysRating());
    }

    @Test
    public void testUpdateRating_Null() {
        Rating updateRatingTest = new Rating("Moodys Rating updatetest", "Sand PRating updatetest", "Fitch Rating updatetest", 40);
        updateRatingTest = ratingService.updateRating(999999999, updateRatingTest);
        assertNull(updateRatingTest);
    }

    @Test
    public void testGetRatingById() {
        Integer ratingIdTest = ratingTest.getId();
        Rating ratingByIdTest = ratingService.getRatingById(ratingIdTest);
        assertNotNull(ratingByIdTest);
        assertEquals("Sand PRating", ratingByIdTest.getSandPRating());
    }

    @Test
    public void testGetRatingById_Null() {
        Rating ratingByIdTest = ratingService.getRatingById(999999999);
        assertNull(ratingByIdTest);
    }

    @Test
    public void testDeleteRatingById() {
        Integer ratingDeleteIdTest = ratingDeleteTest.getId();
        ratingService.deleteRatingById(ratingDeleteIdTest);
        assertNull(ratingService.getRatingById(ratingDeleteIdTest));
    }

    @Test
    public void testDeleteRatingById_False() {
        assertFalse(ratingService.deleteRatingById(999999999));
    }
}