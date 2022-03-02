package com.revature.controller;


import com.revature.dto.PharmacistDTO;

import com.revature.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/pharmacy")

public class PharmacistController {
    @Autowired
    PharmacistService pharmacistService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/addPharmacist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addPharmacist(@RequestBody PharmacistDTO em) {
        try {
            pharmacistService.createPharmacist(em);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully created");
        } catch (Exception e) {

            e.getMessage();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("email already exist");
        }
    }
}
