package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

public class WindowDto {
    private Long id;
    private String name;
    private WindowStatus windowStatus;
    private String roomName;
    private RoomDto room;
    private Long roomId;

    public WindowDto() {
    }

    public WindowDto(Window window) {
        this.id = window.getId();
        this.name = window.getName();
        this.windowStatus = window.getWindowStatus();
        if(window.getRoom()!=null){
            this.roomName = window.getRoom().getName();
            this.roomId = window.getRoom().getId();
            this.room = setRoomDtoFromRoom(window.getRoom()) ;
        }
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
    public RoomDto setRoomDtoFromRoom(Room room){
        RoomDto roomDto=new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setCurrentTemperature(room.getCurremtTemperature());
        roomDto.setTargetTemperature(room.getTargetTemperature());
        roomDto.setFloor(room.getFloor());
    return roomDto;
    }
    public void setName(String name) {
        this.name = name;
    }

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public RoomDto getRoom() {
        return room;
    }

    public void setRoom(RoomDto room) {
        this.room = room;
    }
}
