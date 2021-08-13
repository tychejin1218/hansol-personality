package com.eduhansol.app.services;

import com.eduhansol.app.entities.Group;
import com.eduhansol.app.repositories.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository _groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        _groupRepository = groupRepository;
    }

    @Override
    public Page<Group> getList(Pageable pageable) {
        return _groupRepository.findAll(pageable);
    }

    @Override
    public Page<Group> getListByAdminId(int adminId, Pageable pageable) {
        return _groupRepository.findAllByAdminId(adminId, pageable);
    }

    @Override
    public Page<Group> getListByRegionId(int regionId, Pageable pageable) {
        return _groupRepository.findAllByRegionId(regionId, pageable);
    }

    @Override
    public Page<Group> getListByHqId(int hqId, Pageable pageable) {
        return _groupRepository.findAllByHqId(hqId, pageable);
    }

    @Override
    public Group get(int id) {
        return _groupRepository.findById(id).get();
    }

    @Override
    public Group findFirstByOrderByIdDesc() {
        return _groupRepository.findFirstByOrderByIdDesc();
    }

    @Override
    public int getCount() {
        return (int) _groupRepository.count();
    }

    @Override
    public int getCountByAdminId(int adminId) {
        return (int) _groupRepository.countByAdminId(adminId);
    }

    @Override
    public int getCountByRegionId(int regionId) {
        return (int) _groupRepository.countByRegionId(regionId);
    }

    @Override
    public int getCountByHqId(int hqId) {
        return (int) _groupRepository.countByHqId(hqId);
    }

    @Override
    public Group post(Group model) {
        return _groupRepository.save(model);
    }

    @Override
    public void update(Group model) {
        _groupRepository.save(model);
    }

    @Override
    public void delete(int id) {
        Group group = _groupRepository.findById(id).get();
        if(group == null) return;
        _groupRepository.delete(group);
    }    
}