package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

public class RoomDto {
    private Long id;

    private Integer floor;

    private String name;

    private Double currentTemperature;

    private Double targetTemperature;

    private List<Heater> heaters;

    private List<WindowDto> windows;

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

    public List<Heater> getHeaters() {
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

    public void setHeaters(List<Heater> heaters) {
        this.heaters = heaters;
    }

    public void setWindows(List<WindowDto> windows) {
        this.windows = windows;
    }
}
