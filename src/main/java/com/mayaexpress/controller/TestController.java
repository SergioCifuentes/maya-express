package com.mayaexpress.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<?> testController(){
        return ResponseEntity.ok("Hey baby, I love you so bad <3");
    }

    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    @GetMapping("/user")
    public String helloUser(){
        return "Hello User";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin-user")
    public String helloAdmin(){
        return "Hello Admin";
    }
}
