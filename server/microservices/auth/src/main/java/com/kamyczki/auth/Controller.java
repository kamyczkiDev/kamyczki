package com.kamyczki.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Controller {

    @GetMapping("test")
    String test(){
        return "Hello from port 8081";
    }
}
