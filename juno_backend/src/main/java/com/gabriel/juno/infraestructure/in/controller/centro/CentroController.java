package com.gabriel.juno.infraestructure.in.controller.centro;

import com.gabriel.juno.application.centro.usecase.CentroService;
import com.gabriel.juno.domain.models.centro.Centro;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/centro")
@AllArgsConstructor
public class CentroController {

    private final CentroService service;

    @GetMapping("/findAll")
    public List<Centro> findAll() {
        return service.listCentro();
    }

}
