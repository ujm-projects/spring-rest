package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/heater")
@CrossOrigin
@Transactional
public class HeaterController {
    private final HeaterDao heaterDao;
    private final RoomDao roomDao;
    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) {
        this.heaterDao= heaterDao;
        this.roomDao = roomDao;
    }
    @ApiOperation(value = "GET ALL HEATERS IN THE SYSTEM")
    @GetMapping
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }
    @ApiOperation(value = "GET A SPECIFIC HEATER BY ITS ID IN THE SYSTEM")
    @GetMapping(path = "/{id}")
    public HeaterDto findById(@PathVariable Long id) {
        return heaterDao.findById(id).map(HeaterDto::new).orElse(null);
    }
    @ApiOperation(value = "CHANGE HEATER STATUS : ON  | OFF")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switch")
    public HeaterDto switchStatus(@PathVariable Long id, @RequestParam("status") Integer status) {
        Heater heater= heaterDao.findById(id).orElseThrow(IllegalArgumentException::new);
        heater.setHeaterStatus(status == 1 ? HeaterStatus.ON: HeaterStatus.OFF);
        return new HeaterDto(heater);
    }

    @ApiOperation(value = "CREATE A NEW HEATER BY PASSING VALID HEATER OBJECT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto) {
        Room room = roomDao.getOne(dto.getRoomId());

        Heater heater = null;
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(room, dto.getName(), dto.getHeaterStatus(), dto.getPower()));
        }
        else {
            heater=heaterDao.getOne(dto.getId());
            heater.setHeaterStatus(dto.getHeaterStatus());
        }
        return new HeaterDto(heater);
    }

    @ApiOperation(value = "GET LIST OF HEATERS BY ROOM ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping(path = "/byRoom/{roomId}")
    public List<HeaterDto> findAllByRoomId(@PathVariable("roomId") Long id) {
        return heaterDao.findAllHeaterByRoom(id).stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "DELETE A HEATER BY ITS ID")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        heaterDao.deleteById(id);
    }

}
