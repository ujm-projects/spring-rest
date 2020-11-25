package com.devmind.testinaction.service;

import com.devmind.testinaction.model.Friend;
import com.devmind.testinaction.repository.FriendRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author devmind
 */
public class FriendServiceImpl implements FriendService {

    private FriendRepository friendRepository;
    public FriendServiceImpl(FriendRepository friendRepository) { // 2.
        this.friendRepository = friendRepository;
    }

    @Override
    public int computeFriendAge(Friend friend) {
        if (friend == null) {
            throw new IllegalArgumentException("Friend is required");
        }
        return LocalDate.now().getYear() - friend.getBirthYear();
    }
    @Override
    public double computeFriendAgeAverage() {
        List<Friend> friends = friendRepository.findAll();
        int sumAge = 0;
        for(Friend friend : friends){
            sumAge += computeFriendAge(friend);
        }
        return sumAge * 1.0/ friends.size();
    }
}
