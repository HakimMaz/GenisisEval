package com.evaluation.companyforcontact.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @GetMapping("/hello")
    public String sayHello(){
        return " hello spring";
    }
}
