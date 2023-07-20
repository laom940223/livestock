package com.laron.Livestock.management.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {


    @GetMapping(path = "/hello")
    private String hello(){






     return "Hello";

    }

}
