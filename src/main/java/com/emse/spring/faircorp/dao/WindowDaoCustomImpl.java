package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class WindowDaoCustomImpl implements WindowDaoCustom{
    @PersistenceContext
    private EntityManager em;
    /*
     * find all open windows in  the given room ID
     * ARGS: ROOM_ID
     * RET: LIST_WINDOWS
     * */
    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String jpql = "select w from Window w where w.room.id = :id and w.windowStatus= :status";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .setParameter("status", WindowStatus.OPEN)
                .getResultList();
    }

    /*
     * find all windows in the given building ID
     * ARGS: ROOM_ID
     * RET: LIST_WINDOWS
     * */
    @Override
    public List<Window> findWindowsByBuilding(Long id) {
        String jpql = "select w from Window w " +
                " JOIN Room r ON r.id = w.room.id" +
                " JOIN Building b ON b.id = r.building.id" +
                " WHERE b.id = :id " ;
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .getResultList();
    }
}
