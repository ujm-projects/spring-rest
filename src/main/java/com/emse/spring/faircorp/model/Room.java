package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private Integer floor;

    @Column(nullable=false, length=255)
    private String name;

    @Column(nullable=true)
    private Double currentTemperature;

   @Column(nullable=true)
    private Double targetTemperature;

    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY)
    private List<Heater> heaters;

    @OneToMany(mappedBy = "room" ,fetch = FetchType.LAZY)
    private List<Window> windows;
    public Room(){

    }

    public Room(Long id, Integer floor, String name) {
        this.id = id;
        this.floor = floor;
        this.name = name;
//        currentTemperature = currentTemperature;
//        targetTemperature = targetTemperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurremtTemperature() {
        return currentTemperature;
    }

    public void setCurremtTemperature(Double curremtTemperature) {
        curremtTemperature = curremtTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        targetTemperature = targetTemperature;
    }

    public List<Heater> getHeaters() {
        return heaters;
    }

    public void setHeaters(List<Heater> heaters) {
        this.heaters = heaters;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public void setWindows(List<Window> hwindows) {
        this.windows = hwindows;
    }
}
