package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final BuildingDao buildingDao;
    private final HeaterDao heaterDao;

    public RoomController(WindowDao windowDao, RoomDao roomDao, HeaterDao heaterDao,BuildingDao buildingDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.heaterDao=heaterDao;
        this.buildingDao=buildingDao;
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
    public ResponseEntity findById(@PathVariable Long id) {
        RoomDto room= roomDao.findById(id).map(RoomDto::new).orElse(null);
        if(room==null){
            return new ResponseEntity("ROOM NOT FOUND", HttpStatus.NOT_FOUND) ;
        }
        return new ResponseEntity(room, HttpStatus.OK) ;
    }
    /*
    get room by id
    Args: RoomID
    Ret: RoomDto
    */
    @ApiOperation(value = "GET ALL ROOMs ALONG WITH ITS HEATER AND WINDOWS BY BUILDING ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping(path = "/{id}/building")
    public List<RoomDto> findAllByBuildingId(@PathVariable Long id) {
        return roomDao.findRoomByBuilding(id).stream().map(RoomDto::new).collect(Collectors.toList());
    }
    /*
    change all windows status to inverse of a specific room
    Args: RoomID
    Ret: RoomDto
     */
    @ApiOperation(value = "CHANGE ALL WINDOW STATUS TO ITS INVERSE IN A SPECIFIC ROOM : CLOSE |OPEN ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switchWindowsStatus")
    public ResponseEntity switchWindowStatus(@PathVariable Long id) {
        Room room=roomDao.findById(id).orElse(null);
        if(room==null){
            return new ResponseEntity("ROOM NOT FOUND", HttpStatus.NOT_FOUND);
        }
        List<Window> windows = windowDao.findWindowsByRoom(room.getId());
        windows.forEach(w->{
            w.setWindowStatus(w.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        });
        return  new ResponseEntity(new RoomDto(room),HttpStatus.OK);
    }
    /*
   swtich all windows status OPEN {1} | CLOSE {0}  of a specific room
   Args: RoomID
   Ret: RoomDto
    */
    @ApiOperation(value = "CHANGE ALL WINDOW STATUS TO OPEN {1} | CLOSE {0} IN A SPECIFIC ROOM : CLOSE |OPEN ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switchWindows")
    public ResponseEntity switchWindows(@PathVariable Long id,@RequestParam("status") Integer status ) {
        Room room=roomDao.findById(id).orElse(null);
        if(room==null)
            return new ResponseEntity("ROOM NOT FOUND",HttpStatus.NOT_FOUND);
        List<Window> windows = windowDao.findWindowsByRoom(room.getId());
        windows.forEach(w->{
            w.setWindowStatus(status == 0 ? WindowStatus.CLOSED: WindowStatus.OPEN);
        });
        return new ResponseEntity(new RoomDto(room),HttpStatus.OK);
    }
    /*
    change all heater status to inverse of a specific room
    Args: RoomID
    Ret: RoomDto
     */
    @ApiOperation(value = "CHANGE ALL HEATER STATUS TO ITS INVERSE IN A SPECIFIC ROOM : CLOSE |OPEN ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switchHeatersStatus")
    public ResponseEntity switchHeaterStatus(@PathVariable Long id) {
        Room room=roomDao.findById(id).orElse(null);
        if(room==null)
            return new ResponseEntity("ROOM NOT FOUND",HttpStatus.NOT_FOUND);
        List<Heater> heaters = heaterDao.findAllHeaterByRoom(room.getId());
        heaters.forEach(w->{
            w.setHeaterStatus(w.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
        });
        return new ResponseEntity(new RoomDto(room),HttpStatus.OK);
    }
    /*
    change all heater status ON {1} | OFF {0} of a specific room
    Args: RoomID
    Ret: RoomDto
     */
    @ApiOperation(value = "CHANGE ALL HEATER STATUS TO ON {1} | OFF {0} IN A SPECIFIC ROOM : CLOSE |OPEN ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switchHeaters")
    public ResponseEntity switchHeater(@PathVariable Long id, @RequestParam("status") Integer status) {
        Room room=roomDao.findById(id).orElse(null);
        if(room==null)
            return new ResponseEntity("ROOM NOT FOUND",HttpStatus.NOT_FOUND);
        List<Heater> heaters = heaterDao.findAllHeaterByRoom(room.getId());
        heaters.forEach(w->{
            w.setHeaterStatus(status == 0 ? HeaterStatus.OFF: HeaterStatus.ON);
        });
        return new ResponseEntity(new RoomDto(room),HttpStatus.OK);
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
    public ResponseEntity create(@Validated @RequestBody RoomDto dto) {
        Building building=buildingDao.findById(dto.getBuildingId()).orElse(null);
        if(building==null)
            return new ResponseEntity("ROOM NOT FOUND",HttpStatus.NOT_FOUND);
        Room room= roomDao.save(new Room(dto.getFloor(), dto.getName(),dto.getTargetTemperature(), building ));
        return new ResponseEntity(new RoomDto(room),HttpStatus.CREATED);
    }
    /*
    Delete a specific room and all its resources
    Args: RoomID
    Ret:
    NOTE:THE CASECADE PROPERTY ENABLED TO ROOM HEATER AND WINDOWS
     */
    @ApiOperation(value = "DELETE A ROOM AND ALL ITS RESOURCES INCLUDING HEATERS AND WINDOWS BY PASSING ROOM ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        roomDao.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
