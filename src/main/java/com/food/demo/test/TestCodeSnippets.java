package com.food.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCodeSnippets {
    @GetMapping("/hello")
    public String hello(){
        System.out.println("Our App is working fine.");
        return "All good so far...";
    }
}
