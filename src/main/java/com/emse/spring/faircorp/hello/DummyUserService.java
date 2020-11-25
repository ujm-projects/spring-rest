package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DummyUserService implements UserService{

    @Autowired
    @Qualifier("one")
    GreetingService greetingService;

    @Override
    public void greetAll() {
        List<String> strings=new ArrayList<>();
        strings.add("Elodie");
        strings.add("Charles");
        for(String s:strings){
            greetingService.greet(s);
        }
    }
}
