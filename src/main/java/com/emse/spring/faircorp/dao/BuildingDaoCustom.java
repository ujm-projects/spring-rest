package com.emse.spring.faircorp.dao;


import com.emse.spring.faircorp.model.Building;

public interface BuildingDaoCustom {
    Building findBuildingByName(String name);
}
