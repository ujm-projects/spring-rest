package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoomDaoCustomImpl implements RoomDaoCustom{
    @PersistenceContext
    private EntityManager em;
    /*
     * find room BY given the given room NAME
     * ARGS: ROOM_name
     * RET: ROOM
     * */
    @Override
    public Room findRoomByName(String name) {
        String jpql = "select r from Room r where r.name = :name";
        return em.createQuery(jpql, Room.class)
                .setParameter("name", name)
                .getSingleResult();
    }
    /*
     * find all room by given the given building id
     * ARGS: Building_ID
     * RET: List Rooms
     * */
    @Override
    public List<Room> findRoomByBuilding(Long buildingId) {
        String jpql = "select r from Room r " +
                " JOIN Building b  ON b.id=r.building.id" +
                " where b.id = :id ";
        return em.createQuery(jpql, Room.class)
                .setParameter("id", buildingId)
                .getResultList();
    }
}
