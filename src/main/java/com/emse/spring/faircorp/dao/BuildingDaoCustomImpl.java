package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BuildingDaoCustomImpl  implements BuildingDaoCustom{
    @PersistenceContext
    private EntityManager em;
    @Override
    public Building findBuildingByName(String name) {
        String jpql = "select r from Building r where r.name = :name";
        return em.createQuery(jpql, Building.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
