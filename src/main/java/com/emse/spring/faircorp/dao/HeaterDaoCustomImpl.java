package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Window;

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


    /*
     * find all heaters in the given building ID
     * ARGS: ROOM_ID
     * RET: LIST_HEATER
     * */
    @Override
    public List<Heater> findALlHeaterByBuilding(Long id) {
        String jpql = "select h from heater h " +
                " JOIN Room r ON r.id = h.room.id" +
                " JOIN Building b ON b.id = r.building.id" +
                " WHERE b.id = :id " ;
        return em.createQuery(jpql, Heater.class)
                .setParameter("id", id)
                .getResultList();
    }
    /*
     * find all heaters in the given room ID
     * ARGS: ROOM_ID
     * RET: LIST_HEATERS
     * */
    @Override
    public List<Heater> findAllHeaterByRoom(Long id) {
        String jpql = "select h from Heater h " +
                " JOIN Room r ON r.id = h.room.id" +
                " WHERE r.id = :id " ;
        return em.createQuery(jpql, Heater.class)
                .setParameter("id", id)
                .getResultList();
    }
}
