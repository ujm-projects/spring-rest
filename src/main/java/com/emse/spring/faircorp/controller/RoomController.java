package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
//@CrossOrigin(origins = { "http://localhost:3010" }, maxAge = 3600)
@CrossOrigin
@RequestMapping("/api/room")
@Transactional
public class RoomController {
    private final WindowDao windowDao;
    private final RoomDao roomDao;
    private final HeaterDao heaterDao;

    public RoomController(WindowDao windowDao, RoomDao roomDao, HeaterDao heaterDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.heaterDao=heaterDao;
    }

    /*
    get all rooms
     */
    @ApiOperation(value = "GET ALL ROOMS IN THE SYSTEM")
    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }
    /*
    get room by id
    Args: RoomID
    Ret: RoomDto
     */
    @ApiOperation(value = "GET A SPECIFIC ROOM ALONG WITH ITS HEATER AND WINDOWS")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null);
    }
    /*
    change all windows status to inverse of a specific room
    Args: RoomID
    Ret: RoomDto
     */
    @ApiOperation(value = "CHANGE ALL HEATER AND WINDOW STATUS TO ITS INVERSE IN A SPECIFIC ROOM")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switchWindow")
    public RoomDto switchStatus(@PathVariable Long id) {
        Room room=roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        List<Window> windows = windowDao.findWindowsByRoom(room.getId());
        windows.forEach(w->{
            w.setWindowStatus(w.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        });
        return new RoomDto(room);
    }
    /*
    Create new room
    Args: RoomDto
    Ret: RoomDto
     */
    @ApiOperation(value = "CREATE A NEW ROOM BY PASSING VALID ROOM OBJECR")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!")})
    @PostMapping
    public RoomDto create(@Validated @RequestBody RoomDto dto) {
        Room room= roomDao.save(new Room(dto.getFloor(), dto.getName(),dto.getTargetTemperature() ));
        return new RoomDto(room);
    }
    /*
    Delete a specific room and all its resources
    Args: RoomID
    Ret:
    NOTE:THE CASECADE PROPERTY ENABLED TO ROOM HEATER AND WINDOWS
     */
    @ApiOperation(value = "DELETE A ROOM AND ALL ITS RESOURCES INCLUDING HEATERS AND WINDOWS BY PASSING ROOM ID")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteById(id);
    }

}
