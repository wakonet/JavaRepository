package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.POJO.MensajeResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/backend")
@Api(value = "BackEnd Controller", description = "Operaciones relacionadas con el BackEnd")
public class BackEnd {

    @Value("${version}")
    private String version;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/saludo")
    @ApiOperation(value = "Saludo de bienvenida", response = MensajeResponse.class)
    public MensajeResponse saludar() {
        MensajeResponse respuesta = new MensajeResponse();
        respuesta.setMensaje("Bienvenido Candidato 01 !!!.");
        return respuesta;
    }

    @GetMapping("/version")
    @ApiOperation(value = "Obtener la versión de la aplicación y la fecha y hora de consulta", response = MensajeResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Versión y fecha obtenidas con éxito"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    public MensajeResponse obtenerVersion() {
        MensajeResponse respuesta = new MensajeResponse();

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fechaFormateada = formato.format(new Date());

        respuesta.setMensaje("Versión de la aplicación: " + version + ". Fecha y hora de consulta: " + fechaFormateada);
        return respuesta;
    }
}
