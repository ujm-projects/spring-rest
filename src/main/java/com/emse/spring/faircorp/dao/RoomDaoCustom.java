package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import java.util.List;

public interface RoomDaoCustom {
    Room findRoomByName(String name);
    List<Room> findRoomByBuilding(Long id);
}
