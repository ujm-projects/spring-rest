package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;

import java.util.List;

public interface WindowDaoCustom {
    List<Window> findRoomOpenWindows(Long id);
    List<Window> findWindowsByBuilding(Long buildingId);
    List<Window> findWindowsByRoom(Long roomId);
}
