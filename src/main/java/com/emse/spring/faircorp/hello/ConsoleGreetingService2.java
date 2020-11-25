package com.emse.spring.faircorp.hello;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("two")
public class ConsoleGreetingService2 implements GreetingService{

    @Override
    public void greet(String name) {
        System.out.println(name);
    }

}
