package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.*;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDto {
    private Long id;
    @NotNull
    private Integer floor;
    @NotNull
    private String name;
    private Double currentTemperature;
    @NotNull
    private Double targetTemperature;
    private List<HeaterDto> heaters;
    private List<WindowDto> windows;
    private Long buildingId;
    private Integer noOfOpenWindow;
    private Integer noOfOnHeater;

    public RoomDto(){

    }


    public RoomDto(Room room){
        this.id=room.getId();
        this. floor=room.getFloor();
        this. name=room.getName();
        this. currentTemperature=room.getCurremtTemperature();
        if(room.getTargetTemperature()!=null)
        this.targetTemperature=room.getTargetTemperature();
        if(room.getHeaters()!=null && room.getHeaters().size()>0)
        this. heaters=room.getHeaters().stream().map(HeaterDto::new).collect(Collectors.toList());
        if(room.getWindows()!=null && room.getWindows().size()>0)
        this.windows = room.getWindows().stream().map(WindowDto::new).collect(Collectors.toList());
        if(room.getHeaters()!=null && room.getHeaters().size()>0)
        this.noOfOnHeater=setOnHeater(room);
        if(room.getWindows()!=null && room.getWindows().size()>0)
        this.noOfOpenWindow = setOpenWindow(room);

    }
    private Integer setOnHeater(Room room){
        List<Heater> heater= room.getHeaters().stream().filter(p -> p.getHeaterStatus().equals(HeaterStatus.ON)).collect(Collectors.toList());
        return heater.size();
    }
    private Integer setOpenWindow(Room room){
        List<Window> window= room.getWindows().stream().filter(p -> p.getWindowStatus().equals(WindowStatus.OPEN)).collect(Collectors.toList());
        return window.size();
    }
    public Long getId() {
        return id;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() {
        return buildingId;
    }
    public void setNoOfOnHeater(Integer noOfOnHeater) {
        this.noOfOnHeater = noOfOnHeater;
    }

    public void setNoOfOpenWindow(Integer noOfOpenWindow) {
        this.noOfOpenWindow = noOfOpenWindow;
    }

    public Integer getNoOfOnHeater() {
        return noOfOnHeater;
    }

    public Integer getNoOfOpenWindow() {
        return noOfOpenWindow;
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
