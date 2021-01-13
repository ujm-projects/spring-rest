package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.BuildingDto;
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
@RequestMapping("/api/building")
@CrossOrigin
@Transactional
public class BuildingController {
    private final BuildingDao buildingDao;
    private final WindowDao windowDao;
    private final RoomDao roomDao;
    private final HeaterDao heaterDao;

    public BuildingController(WindowDao windowDao, RoomDao roomDao, HeaterDao heaterDao, BuildingDao buildingDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
        this.heaterDao=heaterDao;
        this.buildingDao=buildingDao;
    }

    /*
    get all Buildings
     */
    @ApiOperation(value = "GET ALL BUILDINGS IN THE SYSTEM")
    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }
    /*
    get building by id
    Args: BuildingID
    Ret: BuildingDto
     */
    @ApiOperation(value = "GET A SPECIFIC BUILDING ALONG WITH ITS ROOMS AND WINDOWS AND HEATERS")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        BuildingDto building= buildingDao.findById(id).map(BuildingDto::new).orElse(null);
        if(building==null){
            return new ResponseEntity("WINDOW NOT FOUND",HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity(building,HttpStatus.OK);
    }
    /*
    Create new room
    Args: RoomDto
    Ret: RoomDto
     */
    @ApiOperation(value = "CREATE A NEW BUILDING BY PASSING VALID BUILDING OBJECT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!")})
    @PostMapping
    public ResponseEntity create(@Validated @RequestBody BuildingDto dto) {
        Building building= buildingDao.save(new Building(dto.getName(), dto.getOutsideTemperature() ));
        BuildingDto bto=new BuildingDto(building);
        return new  ResponseEntity(bto, HttpStatus.OK) ;
    }
    /*
    Delete a specific building and all its resources
    Args: BuildingID
    Ret:
     */
    @ApiOperation(value = "DELETE A BUILDING AND ALL ITS RESOURCES INCLUDING ROOMS, HEATERS AND WINDOWS BY PASSING BUILDING ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        buildingDao.deleteById(id);
        return new  ResponseEntity(HttpStatus.OK) ;
    }
    /*
       swtich all windows status OPEN {1} | CLOSE {0}  of a specific Building
       Args: RoomID
       Ret: RoomDto
    */
    @ApiOperation(value = "CHANGE ALL WINDOW STATUS TO OPEN {1} | CLOSE {0} IN A SPECIFIC BUILDING : CLOSE |OPEN ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switchWindows")
    public ResponseEntity switchWindows(@PathVariable Long id,@RequestParam("status") Integer status ) {
        Building building=buildingDao.findById(id).orElse(null);
        if(building==null)
            return new ResponseEntity("BUILDING NOT FOUND",HttpStatus.NOT_FOUND);
        List<Room> rooms=roomDao.findRoomByBuilding(building.getId());
        if(rooms.size()<1)
            return new ResponseEntity("BUILDING HAS NO ROOM",HttpStatus.NOT_FOUND);
        rooms.forEach(r->{
            List<Window> windows = windowDao.findWindowsByRoom(r.getId());
            windows.forEach(w->{
                w.setWindowStatus(status == 0 ? WindowStatus.CLOSED: WindowStatus.OPEN);
            });
        });
        return new ResponseEntity(new BuildingDto(building),HttpStatus.OK);
    }
    /*
    change all heater status ON {1} | OFF {0} of a specific building
    Args: RoomID
    Ret: RoomDto
     */
    @ApiOperation(value = "CHANGE ALL HEATER STATUS TO ON {1} | OFF {0} IN A SPECIFIC BUILDING : CLOSE |OPEN ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switchHeaters")
    public ResponseEntity switchHeater(@PathVariable Long id, @RequestParam("status") Integer status) {
        Building building=buildingDao.findById(id).orElse(null);
        if(building==null)
            return new ResponseEntity("BUILDING NOT FOUND",HttpStatus.NOT_FOUND);
        List<Room> rooms=roomDao.findRoomByBuilding(building.getId());
        if(rooms.size()<1)
            return new ResponseEntity("BUILDING HAS NO ROOM",HttpStatus.NOT_FOUND);
        rooms.forEach(r->{
            List<Heater> heaters = heaterDao.findAllHeaterByRoom(r.getId());
            heaters.forEach(w->{
                w.setHeaterStatus(status == 0 ? HeaterStatus.OFF: HeaterStatus.ON);
            });
        });
        return new ResponseEntity(new BuildingDto(building),HttpStatus.OK);
    }
    /*
     UPDATE BUILDING NAME AND OUTSIDE TEMP
     Args: BUILDING DTO
     Ret: BUILDING_DTO
      */
    @ApiOperation(value = "UPDATE BUILDING NAME AND OUTSIDE TEMP ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}")
    public ResponseEntity updateBuilding(@PathVariable Long id,@RequestBody BuildingDto dto ) {
        Building building=buildingDao.findById(dto.getId()).orElse(null);
        if(building==null)
            return new ResponseEntity("BUILDING NOT FOUND",HttpStatus.NOT_FOUND);
       building.setName(dto.getName());
       building.setOutsideTemperature(dto.getOutsideTemperature());
       return new ResponseEntity(new BuildingDto(building),HttpStatus.OK);
    }
}
