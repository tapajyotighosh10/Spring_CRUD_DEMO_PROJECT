package com.exampleSpring.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
@Slf4j
public class HomeController {

    @RequestMapping("/test")
    public String test() {
        log.warn("This is a warning message");
        return "Testing message";
    }
}
