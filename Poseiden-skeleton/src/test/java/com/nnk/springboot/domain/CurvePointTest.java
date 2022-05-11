package com.nnk.springboot.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CurvePointTest {

    private CurvePoint curvePoint;

    @Test
    public void setAndGetId() {
        curvePoint = new CurvePoint();
        curvePoint.setId(1);
        Assert.assertTrue(curvePoint.getId() == 1);
    }

    @Test
    public void setAndGetCurveId() {
        curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        Assert.assertTrue(curvePoint.getCurveId() == 1);
    }

    @Test
    public void setAndGetTerm() {
        curvePoint = new CurvePoint();
        curvePoint.setTerm(10.0);
        Assert.assertTrue(curvePoint.getTerm() == 10.0);
    }

    @Test
    public void setAndGetValue() {
        curvePoint = new CurvePoint();
        curvePoint.setValue(10.0);
        Assert.assertTrue(curvePoint.getValue() == 10.0);
    }

}