package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${loaded-config}")
    private String loadedConfig;

    @RequestMapping("/")
    public String test() {
        System.out.println("enter client test");
        return "hello world from client";
    }

    @RequestMapping("/configs")
    public String config() {
        System.out.println("print config");
        return loadedConfig;
    }
}
