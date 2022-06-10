package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.CurvePoint;

public interface CurvePointService {

    List<CurvePoint> getAllCurvePoint();

    CurvePoint saveCurvePoint(CurvePoint curvePoint);

    CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint);

    CurvePoint getCurvePointById(Integer id);

    boolean deleteCurvePointById(Integer id);

}