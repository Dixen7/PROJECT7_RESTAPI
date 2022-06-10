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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@SpringBootTest
public class CurvePointServiceTest {

    @Autowired
    private CurvePointService curvePointService;
    @Autowired
    private CurvePointRepository curvePointRepository;

    CurvePoint curvePointTest = new CurvePoint(10, 10d, 30d);
    CurvePoint curvePointDeleteTest = new CurvePoint(20, 20d, 40d);
    CurvePoint curvePointUpdateTest = new CurvePoint(30, 30d, 50d);

    @BeforeEach
    public void setDb() {
        curvePointRepository.deleteAll();
        curvePointService.saveCurvePoint(curvePointTest);
        curvePointService.saveCurvePoint(curvePointDeleteTest);
        curvePointService.saveCurvePoint(curvePointUpdateTest);
    }

    @Test
    public void testGetAllCurvePoint() {
        List<CurvePoint> curePointListTest = curvePointService.getAllCurvePoint();
        assertNotNull(curePointListTest);
        assertTrue(curePointListTest.size()>0);
    }

//    @Test
//    public void testSaveCurvePoint() {
//        CurvePoint saveCurvePointTest = new CurvePoint(30, 30d, 50d);
//        saveCurvePointTest = curvePointService.saveCurvePoint(saveCurvePointTest);
//        assertNotNull(saveCurvePointTest);
//        assertEquals(30d,saveCurvePointTest.getTerm());
//    }

    @Test
    public void testSaveCurvePoint_Null() {
        CurvePoint saveCurvePointTest = null;
        saveCurvePointTest = curvePointService.saveCurvePoint(saveCurvePointTest);
        assertNull(saveCurvePointTest);
    }

//    @Test
//    public void testUpdateCurvePoint() {
//        Integer curveUpdateIdTest = curvePointDeleteTest.getId();
//        CurvePoint updateCurvePointTest = new CurvePoint(40, 40d, 60d);
//        updateCurvePointTest = curvePointService.updateCurvePoint(curveUpdateIdTest, updateCurvePointTest);
//        assertNotNull(updateCurvePointTest);
//        assertEquals(40d,updateCurvePointTest.getTerm());
//    }

    @Test
    public void testUpdateCurvePoint_Null() {
        CurvePoint updateCurvePointTest = new CurvePoint(40, 40d, 60d);
        updateCurvePointTest = curvePointService.updateCurvePoint(999999999, updateCurvePointTest);
        assertNull(updateCurvePointTest);
    }

//    @Test
//    public void testGetCurvePointById() {
//        Integer curveIdTest = curvePointTest.getId();
//        CurvePoint curveByIdTest = curvePointService.getCurvePointById(curveIdTest);
//        assertNotNull(curveByIdTest);
//        assertEquals(curveByIdTest);
//    }

    @Test
    public void testGetCurvePointById_Null() {
        CurvePoint curveByIdTest = curvePointService.getCurvePointById(999999999);
        assertNull(curveByIdTest);
    }

    @Test
    public void testDeleteCurvePointById() {
        Integer curveDeleteIdTest = curvePointDeleteTest.getId();
        curvePointService.deleteCurvePointById(curveDeleteIdTest);
        assertNull(curvePointService.getCurvePointById(curveDeleteIdTest));
    }

    @Test
    public void testDeleteCurvePointById_False() {
        assertFalse(curvePointService.deleteCurvePointById(999999999));
    }
}