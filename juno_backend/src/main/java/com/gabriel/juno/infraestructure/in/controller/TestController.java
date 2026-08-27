package com.gabriel.juno.infraestructure.in.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    ResponseEntity getConexionCodeTest()  {
        Logger.getLogger(TestController.class.getName()).log(Level.INFO, "getConexionCodeTest");
        return  ResponseEntity.ok().build();
    }
}
