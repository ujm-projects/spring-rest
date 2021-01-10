package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;

import java.util.List;

public interface HeaterDaoCustom {
    List<Heater> findRoomOnHeater(Long id);
    List<Heater> findRoomOffHeater(Long id);
    Integer deleteHeaterByRoom(Long id);
}
