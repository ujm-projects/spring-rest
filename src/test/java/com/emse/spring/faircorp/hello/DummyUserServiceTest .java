package com.emse.spring.faircorp.hello;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(OutputCaptureExtension.class)
@ExtendWith(SpringExtension.class) // (This anotation load spring context in class start
class DummyUserServiceTest  {
    @Configuration // (2)We have to configure how the context is loaded. In our case we added @ComponentScan("com.emse.spring.faircorp.hello") to help Spring to found our classes. In our app this scan is made by SpringBoot, but in our test SpringBoot is not loaded
    @ComponentScan("com.emse.spring.faircorp.hello")
    public static class DummyUserServiceTestConfig{}

    @Autowired // (3)As our test has is own Spring Context we can inject inside the bean to test#
    public DummyUserService dummyUserService;

    @Test
    public void testGreetingAll(CapturedOutput output) {
        dummyUserService.greetAll();
        Assertions.assertThat(output).contains("Elodie", "harles");
    }
}
