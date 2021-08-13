package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Orientation;
import com.eduhansol.app.repositories.OrientationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrientationServiceImpl implements OrientationService {

    private final OrientationRepository _orientationRepository;

    @Autowired
    public OrientationServiceImpl(OrientationRepository orientationRepository) {
        _orientationRepository = orientationRepository;
    }

    @Override
    public List<Orientation> getList(int normPersonalityId) {
        return _orientationRepository.findByNormPersonalityId(normPersonalityId);
    }

    @Override
    public Orientation get(int normPersonalityId, int pageNo) {
        return _orientationRepository.findByNormPersonalityIdAndPageNo(normPersonalityId, pageNo);
    }

}