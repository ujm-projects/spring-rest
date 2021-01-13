package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingDto {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Double outsideTemperature;

    private List<RoomDto> rooms;

    public BuildingDto(){

    }
    public BuildingDto(Building building){
        this.id=building.getId();
        this.name=building.getName();
        this.outsideTemperature=building.getOutsideTemperature();
        if(building.getRooms()!=null && building.getRooms().size()>0)
        this.rooms=building.getRooms().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public List<RoomDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDto> rooms) {
        this.rooms = rooms;
    }
}
