package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDto {
    private Long id;
    private Integer floor;
    private String name;
    private Double currentTemperature;
    private Double targetTemperature;
    private List<HeaterDto> heaters;
    private List<WindowDto> windows;
    public RoomDto(){

    }
    public RoomDto(Room room){
        this.id=room.getId();
        this. floor=room.getFloor();
        this. name=room.getName();
        this. currentTemperature=room.getCurremtTemperature();
        this. targetTemperature=room.getTargetTemperature();
        this. heaters=room.getHeaters().stream().map(HeaterDto::new).collect(Collectors.toList());
        this. windows=room.getWindows().stream().map(WindowDto::new).collect(Collectors.toList());
    }
    public Long getId() {
        return id;
    }

    public Integer getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public List<HeaterDto> getHeaters() {
        return heaters;
    }

    public List<WindowDto> getWindows() {
        return windows;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public void setHeaters(List<HeaterDto> heaters) {
        this.heaters = heaters;
    }

    public void setWindows(List<WindowDto> windows) {
        this.windows = windows;
    }
}
