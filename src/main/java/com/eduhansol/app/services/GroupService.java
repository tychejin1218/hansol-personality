package com.eduhansol.app.services;

import com.eduhansol.app.entities.Group;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Page<Group> getList(Pageable pageable);
    Page<Group> getListByAdminId(int adminId, Pageable pageable);
    Page<Group> getListByRegionId(int regionId, Pageable pageable);
    Page<Group> getListByHqId(int hqId, Pageable pageable);

    Group get(int id);
    Group findFirstByOrderByIdDesc();

    int getCount();
    int getCountByAdminId(int adminId);
    int getCountByRegionId(int regionId);
    int getCountByHqId(int hqId);

    Group post(Group model);

    void update(Group model);

    void delete(int id);
}