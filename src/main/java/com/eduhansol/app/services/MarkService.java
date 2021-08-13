package com.eduhansol.app.services;

import com.eduhansol.app.entities.Mark;

public interface MarkService {
    Mark get(int testerId);
    Mark post(Mark model);
    void delete(int testerId);
}