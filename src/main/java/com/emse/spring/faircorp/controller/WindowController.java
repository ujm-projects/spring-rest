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
    public WindowDto findById(@PathVariable Long id) {
        return windowDao.findById(id).map(WindowDto::new).orElse(null);
    }

    @ApiOperation(value = "CHANGE STATUS OF THE WINDOW: CLOSE | OPEN")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping(path = "/{id}/switch")
    public WindowDto switchStatus(@PathVariable Long id) {
        Window window = windowDao.findById(id).orElseThrow(IllegalArgumentException::new);
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new WindowDto(window);
    }

//    @ApiOperation(value = "Get list of Windows by room id ", response = Iterable.class, tags = "")
    @ApiOperation(value = "GET LIST OF WINDOWS BY ROOM ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping(path = "/byRoom/{roomId}")
    public List<WindowDto> findAllByRoomId(@PathVariable Long id) {
        return windowDao.findWindowsByRoom(id).stream().map(WindowDto::new).collect(Collectors.toList());
    }

    @ApiOperation(value = "CREATE A NEW WINDOW BY PASSING A WINDOW OBJECT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!") })
    @PostMapping
    public WindowDto create(@RequestBody WindowDto dto) {
        Room room = roomDao.getOne(dto.getRoomId());
        Window window = null;
        if (dto.getId() == null) {
            window = windowDao.save(new Window(room, dto.getName(), dto.getWindowStatus()));
        }
        else {
            window = windowDao.getOne(dto.getId());
            window.setWindowStatus(dto.getWindowStatus());
        }
        return new WindowDto(window);
    }
    @ApiOperation(value = "DELETE A WINDOW BY ITS ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        windowDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
