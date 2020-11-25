package com.devmind.testinaction.service;

import com.devmind.testinaction.model.Friend;

/**
 * @author devmind
 */
public interface FriendService {

    /**
     * Compute friend age from his birth year
     */
    double computeFriendAgeAverage();
    int computeFriendAge(Friend friend);
}
