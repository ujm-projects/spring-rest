package com.devmind.testinaction.service;

import com.devmind.testinaction.model.Friend;
import com.devmind.testinaction.repository.FriendRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FriendServiceImplTest {
    @Mock // 2.
    private FriendRepository friendRepostiory;

    @InjectMocks //3.
    private FriendServiceImpl friendService;

//    @BeforeEach // 1.
//    public void init(){
//        friendRepostiory=new FriendRepository() {
//            @Override
//            public List<Friend> findAll() {
//                List<Friend> friendList=new ArrayList<>();
//                Friend friend1=new Friend("Elodie", 1999);
//                Friend friend2=new Friend("Charles", 2001);
//                friendList.add(friend1);
//                friendList.add(friend2);
//                return friendList;
//            }
//        };
//    }
    @Test
    void computeFriendAge() {
        List<Friend> myFriends = Arrays.asList(
                new Friend("Elodie", 1999),
                new Friend("Charles", 2001));

        Mockito.when(friendRepostiory.findAll()).thenReturn(myFriends); // 4.
        double average = friendService.computeFriendAgeAverage();
        List<Friend>friends= friendRepostiory.findAll();
//        Assertions.assertThat(friends)
//                .hasSize(2)
//                .extracting(Friend::getName, Friend::getBirthYear)
//                .containsExactlyInAnyOrder(
//                        Tuple.tuple("Elodie", 1999),
//                        Tuple.tuple("Charles", 2001));
        Assertions.assertThat(average).isEqualTo(20.0);
    }


}