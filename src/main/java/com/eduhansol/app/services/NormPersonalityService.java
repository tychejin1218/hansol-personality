package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.NormPersonality;

public interface NormPersonalityService{
    List<NormPersonality> getList();
    NormPersonality get(int id);
}