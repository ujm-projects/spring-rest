package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/window")
@CrossOrigin
@Transactional
public class WindowController {
    private final WindowDao windowDao;
    private final RoomDao roomDao;

    public WindowController(WindowDao windowDao, RoomDao roomDao) {
        this.windowDao = windowDao;
        this.roomDao = roomDao;
    }

    @ApiOperation(value = "GET ALL THE WINDOWS IN THE SYSTEM")
    @GetMapping
    public List<WindowDto> findAll() {
        return windowDao.findAll().stream().map(WindowDto::new).collect(Collectors.toList());
    }
    @ApiOperation(value = "GET A SPECIFIC WINDOW BY ITS ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        WindowDto window=windowDao.findById(id).map(WindowDto::new).orElse(null);
        if(window==null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity(window, HttpStatus.OK);
    }

    @Deprecated
    @ApiOperation(value = "CHANGE STATUS OF THE WINDOW: CLOSE | OPEN")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switch")
    public ResponseEntity switchStatus(@PathVariable Long id) {
        Window window = windowDao.findById(id).orElse(null);
        if(window==null)
        {return new ResponseEntity("WINDOW NOT FOUND",HttpStatus.NOT_FOUND);}
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new ResponseEntity(new WindowDto(window), HttpStatus.OK);
    }

    @ApiOperation(value = "CHANGE STATUS OF THE WINDOW: CLOSE | OPEN - Version2")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switch-v2")
    public ResponseEntity switchStatusV2(@PathVariable Long id, @RequestParam("status") Integer status ) {
        Window window = windowDao.findById(id).orElse(null);
        if(window==null){
            return new ResponseEntity("WINDOW NOT FOUND",HttpStatus.NOT_FOUND);
        }
        window.setWindowStatus(status == 0 ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new ResponseEntity(new WindowDto(window),HttpStatus.OK);
    }

    @ApiOperation(value = "GET LIST OF WINDOWS BY ROOM ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping(path = "/byRoom/{roomId}")
    public List<WindowDto> findAllByRoomId(@PathVariable("roomId") Long id) {
        return windowDao.findWindowsByRoom(id).stream().map(WindowDto::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "CREATE A NEW WINDOW BY PASSING A WINDOW OBJECT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!") })
    @PostMapping
    public ResponseEntity create(@RequestBody WindowDto dto) {
        Room room = roomDao.findById(dto.getRoomId()).orElse(null);
        if (room == null) {
           return new ResponseEntity("ROOM NOT FOUND",HttpStatus.NOT_FOUND);
        }
        Window window = null;
        if (dto.getId() == null) {
            window = windowDao.save(new Window(room, dto.getName(), dto.getWindowStatus()));
        }
        else {
            window = windowDao.getOne(dto.getId());
            window.setWindowStatus(dto.getWindowStatus());
        }
        return new ResponseEntity(new WindowDto(window),HttpStatus.CREATED);
    }
    @ApiOperation(value = "DELETE A WINDOW BY ITS ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        windowDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
