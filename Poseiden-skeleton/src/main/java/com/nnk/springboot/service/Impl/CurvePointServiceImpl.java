package com.nnk.springboot.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.nnk.springboot.service.CurvePointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointServiceImpl implements CurvePointService {

    private static Logger logger = LoggerFactory.getLogger(CurvePointServiceImpl.class);

    private CurvePointRepository curvePointRepository;

    public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    @Override
    public List<CurvePoint> getAllCurvePoint() {
        logger.info("Getting a list of all CurvePoint");
        return curvePointRepository.findAll();
    }

    @Override
    @Transactional
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        if(Objects.isNull(curvePoint)) {
            logger.error("New CurvePoint must not be null");
            return null;
        }
        logger.info("Creating a New CurvePoint");
        return curvePointRepository.save(curvePoint);
    }

    @Override
    @Transactional
    public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint) {
        Optional<CurvePoint> optCurvePoint = curvePointRepository.findById(id);
        if(optCurvePoint.isPresent()) {
            CurvePoint updatingCurvePoint = optCurvePoint.get();
            updatingCurvePoint.setCurveId(curvePoint.getCurveId());
            updatingCurvePoint.setTerm(curvePoint.getTerm());
            updatingCurvePoint.setValue(curvePoint.getValue());
            logger.info("CurvePoint " + id + " updated");
            return curvePointRepository.save(updatingCurvePoint);
        } else {
            logger.error("CurvePoint with id: " + id + " not found nor updated");
            return null;
        }
    }

    @Override
    public CurvePoint getCurvePointById(Integer id) {
        Optional<CurvePoint> optCurvePoint = curvePointRepository.findById(id);
        if(optCurvePoint.isPresent()) {
            logger.info("CurvePoint with id " + id + " found");
            return optCurvePoint.get();
        } else {
            logger.error("CurvePoint with id: " + id + " not found");
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteCurvePointById(Integer id) {
        Optional<CurvePoint> optCurvePoint = curvePointRepository.findById(id);
        if(optCurvePoint.isPresent()) {
            curvePointRepository.deleteById(id);
            logger.info("CurvePoint with id " + id + " deleted");
            return true;
        } else {
            logger.error("CurvePoint with id: " + id + " not found nor deleted");
            return false;
        }
    }

}