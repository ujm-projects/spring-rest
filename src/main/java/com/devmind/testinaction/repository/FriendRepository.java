package com.devmind.testinaction.repository;

import com.devmind.testinaction.model.Friend;

import java.util.List;

/**
 * @author devmind
 */
public interface FriendRepository {

    List<Friend> findAll();

}
