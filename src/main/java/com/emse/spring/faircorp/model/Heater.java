package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
@Table(name = "HEATER")
public class Heater {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false, length=255)
    private String name;

    @Column(nullable=true)
    private  Long power;

//    @Column(nullable=false)
    @ManyToOne
    private Room room;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private HeaterStatus heaterStatus;

    public Heater(){
    }

    public Heater(Room room, String name,  HeaterStatus heaterStatus) {
        this.room  =room;
        this.name = name;
        this.heaterStatus=  heaterStatus;
    }
    public Heater(Room room, Long id, String name,  HeaterStatus heaterStatus) {
        this.room  =room;
        this.id = id;
        this.name = name;
        this.heaterStatus=  heaterStatus;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPower() {
        return power;
    }

    public Room getRoom() {
        return room;
    }
//    public Room getHeaterStatus() {
//        return heaterStatus;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }
}
