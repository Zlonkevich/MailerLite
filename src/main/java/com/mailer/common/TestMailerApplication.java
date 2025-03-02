package com.mailer.common;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com"})
@SpringBootApplication
public class TestMailerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TestMailerApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
    }
}
