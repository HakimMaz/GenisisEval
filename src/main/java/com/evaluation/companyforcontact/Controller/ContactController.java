package com.evaluation.companyforcontact.Controller;

import com.evaluation.companyforcontact.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
   @PostMapping("/create")
    public ResponseEntity<Contact> create(){
       return null;
   }
}
