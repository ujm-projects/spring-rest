package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WindowDao  extends JpaRepository<Window, Long>, WindowDaoCustom {

    /*
    Delete all windows by given room id
    Args: Room Id
    Ret : int: deleted count
     */
    @Modifying
    @Query(value = "delete from RWindow w where w.ROOM_ID = :roomId", nativeQuery = true )
    int deleteWindowByRoom(@Param("roomId") Long roomId);

}
