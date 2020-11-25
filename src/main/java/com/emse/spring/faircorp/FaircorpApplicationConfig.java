package com.emse.spring.faircorp;

import com.emse.spring.faircorp.hello.ConsoleGreetingService;
import com.emse.spring.faircorp.hello.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class FaircorpApplicationConfig {
    @Bean
    public CommandLineRunner greetingCommandLine(@Qualifier("two") GreetingService greetingService) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                greetingService.greet("Hello, world");
            }
        };
    }

}