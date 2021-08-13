package com.eduhansol.app.services;

import com.eduhansol.app.entities.Info;
import com.eduhansol.app.repositories.InfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    private final InfoRepository infoRepository;

    @Autowired
    public InfoServiceImpl(InfoRepository infoRepository){
        this.infoRepository = infoRepository;
    }

    @Override
    public Info findById(int id) {
        return this.infoRepository.findById(id).get();
    }

    @Override
    public void save(Info info) {
        this.infoRepository.save(info);
    }
}