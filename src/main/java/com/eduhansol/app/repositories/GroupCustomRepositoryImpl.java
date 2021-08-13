package com.eduhansol.app.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.eduhansol.app.entities.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class GroupCustomRepositoryImpl implements GroupCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<Group> findAllByRegionId(int regionId, Pageable pageable) {
        String jpql = "SELECT g FROM Group g " + 
                      "inner join g.admin a " + 
                      "inner join a.department d " + 
                      "inner join d.region r " + 
                      "inner join r.hq h " + 
                      "where r.id = :regionId";
        TypedQuery<Group> query = entityManager.createQuery(jpql, Group.class);
        query.setParameter("regionId", regionId);

        List<Group> groups = query.getResultList();
        long start = pageable.getOffset();
        long end = (start + pageable.getPageSize()) > groups.size() ? groups.size() : (start + pageable.getPageSize());
        Page<Group> pages = new PageImpl<Group>(groups.subList((int) start, (int) end), pageable, groups.size());
        return pages;
    }

    @Override
    public long countByRegionId(int regionId) {
        String jpql = "SELECT count(g.id) FROM Group g " + 
                      "inner join g.admin a " + 
                      "inner join a.department d " + 
                      "inner join d.region r " + 
                      "inner join r.hq h " + 
                      "where r.id = :regionId";
        Query query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("regionId", regionId);

        long count = (long) query.getSingleResult();
        return count;
    }

    @Override
    public Page<Group> findAllByHqId(int hqId, Pageable pageable) {
        String jpql = "SELECT g FROM Group g " + 
                      "inner join g.admin a " + 
                      "inner join a.department d " + 
                      "inner join d.region r " + 
                      "inner join r.hq h " + 
                      "where h.id = :hqId";
        TypedQuery<Group> query = entityManager.createQuery(jpql, Group.class);
        query.setParameter("hqId", hqId);

        List<Group> groups = query.getResultList();
        long start = pageable.getOffset();
        long end = (start + pageable.getPageSize()) > groups.size() ? groups.size() : (start + pageable.getPageSize());
        Page<Group> pages = new PageImpl<Group>(groups.subList((int) start, (int) end), pageable, groups.size());
        return pages;
    }

    @Override
    public long countByHqId(int hqId) {
        String jpql = "SELECT count(g.id) FROM Group g " + 
                      "inner join g.admin a " + 
                      "inner join a.department d " + 
                      "inner join d.region r " + 
                      "inner join r.hq h " + 
                      "where h.id = :hqId";
        Query query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("hqId", hqId);

        long count = (long) query.getSingleResult();
        return count;
    }

}