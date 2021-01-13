package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;

import javax.persistence.*;

public class HeaterDto {
    private Long id;
    private String name;
    private  Long power;
    private HeaterStatus heaterStatus;
    private String roomName;
    private RoomDto room;
    private Long roomId;
    public HeaterDto(){
    }

    public HeaterDto(Heater heater) {
        this.id = heater.getId();
        this.name = heater.getName();
        this.heaterStatus=  heater.getHeaterStatus();
        if(heater.getRoom()!=null) {
            this.roomName = heater.getRoom().getName();
            this.roomId = heater.getRoom().getId();
            this.room = setRoomDtoFronRoom(heater.getRoom());
        }
    }
    public RoomDto setRoomDtoFronRoom(Room room){
        RoomDto roomDto=new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setCurrentTemperature(room.getCurremtTemperature());
        roomDto.setTargetTemperature(room.getTargetTemperature());
        roomDto.setFloor(room.getFloor());
        return roomDto;
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

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
