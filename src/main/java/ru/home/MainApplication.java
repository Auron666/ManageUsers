package ru.home;


import com.vaadin.flow.spring.annotation.EnableVaadin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MainApplication {


    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);

        log.info("Start Application");
    }
}
