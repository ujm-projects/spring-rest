package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class HeaterDaoCustomImpl implements HeaterDaoCustom{
    @PersistenceContext
    private EntityManager em;

    /*
     * find all open heater in given the given room ID
     * ARGS: ROOM_ID
     * RET: LIST_Heater
     * */
    @Override
    public List<Heater> findRoomOnHeater(Long id) {
        String jpql = "select h from Heater h where h.room.id = :id and h.heaterStatus= :status";
        return em.createQuery(jpql, Heater.class)
                .setParameter("id", id)
                .setParameter("status", HeaterStatus.ON)
                .getResultList();
    }
    /*
     * find all off heater in given the given room ID
     * ARGS: ROOM_ID
     * RET: LIST_Heater
     * */
    @Override
    public List<Heater> findRoomOffHeater(Long id) {
        String jpql = "select h from Heater h where h.room.id = :id and h.heaterStatus= :status";
        return em.createQuery(jpql, Heater.class)
                .setParameter("id", id)
                .setParameter("status", HeaterStatus.OFF)
                .getResultList();
    }
    /*
     * delete all heater by given room ID
     * ARGS: ROOM_ID
     * RET:
     * */
    @Override
    public Integer deleteHeaterByRoom(Long id) {
        String jpql = "delete from Heater h where h.room.id = :id";
        return em.createQuery(jpql)
                .setParameter("id", id)
                .executeUpdate();
    }
}
