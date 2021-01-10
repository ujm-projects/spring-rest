package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HeaterDao  extends JpaRepository<Heater, Long>, HeaterDaoCustom {

    @Modifying
    @Query("delete from Heater r where r.room.id = ?1")
    void deleteHeaterByRoom(Long roomId);
}
