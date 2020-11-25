package com.emse.spring.faircorp.dao;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RoomDaoTest {
    @Autowired
    private RoomDao roomDao;

    @Test
    public void shouldFindOneRoom() {
        Room room = roomDao.getOne(-10L);
        Assertions.assertThat(room.getName()).isEqualTo("Room 1");
//        Assertions.assertThat(window.getWindowStatus()).isEqualTo(WindowStatus.CLOSED);
    }

}
