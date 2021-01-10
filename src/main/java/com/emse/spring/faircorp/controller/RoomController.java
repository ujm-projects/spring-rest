package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null);
    }

    @PutMapping(path = "/{id}/switchWindow")
    public RoomDto switchStatus(@PathVariable Long id) {
        List<Window> windows = windowDao.find(id).orElseThrow(IllegalArgumentException::new);
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new WindowDto(window);
    }

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
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(id);
    }

}
