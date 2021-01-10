package com.emse.spring.faircorp.dao;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.Room;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class HeaterDaoTest {

    @Autowired
    private HeaterDao heaterDao;
    @Autowired
    private RoomDao roomDao;
    /*
     * find one heater in given the given heater ID
     * ARGS: HEATER_ID
     * RET: Heater
     * */
    @Test
    public void shouldFindAHeater() {
        Heater heater = heaterDao.getOne(-10L);
        Assertions.assertThat(heater.getName()).isEqualTo("Heater1");
        Assertions.assertThat(heater.getHeaterStatus()).isEqualTo(HeaterStatus.ON);
    }

    /*
    * find all open heater in given the given room ID
    * ARGS: ROOM_ID
    * RET: LIST_Heater
    * */
    @Test
    public void shouldFindRoomOpenHeater() {
        List<Heater> result = heaterDao.findRoomOnHeater(-10L);
        Assertions.assertThat(result)
                .hasSize(2)
                .extracting("id", "heaterStatus")
                .containsExactly(Tuple.tuple(-10L, HeaterStatus.ON) ,
                        Tuple.tuple(-9L, HeaterStatus.ON));
    }
    /*
     * find all off heater in  given room ID
     * ARGS: ROOM_ID
     * RET: LIST_Heater
     * */
    @Test
    public void shouldNotFindRoomOffHeater() {
        List<Heater> result = heaterDao.findRoomOffHeater(-10L);
        Assertions.assertThat(result).isEmpty();
    }

    /*
     * delete all off heater in given room ID
     * ARGS: ROOM_ID
     * RET: int
     * */
    @Test
    public void shouldDeleteHeatersRoom() {
        Room room = roomDao.getOne(-10L);
        List<Long> heaterIds= room.getHeaters().stream().map(Heater::getId).collect(Collectors.toList());
        Assertions.assertThat(heaterIds.size()).isEqualTo(2);

        heaterDao.deleteHeaterByRoom(-10L);
        List<Heater> result =heaterDao.findAllById(heaterIds);
        Assertions.assertThat(result).isEmpty();

    }
}
