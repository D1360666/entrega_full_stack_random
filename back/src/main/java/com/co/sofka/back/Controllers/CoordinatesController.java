package com.co.sofka.back.Controllers;


import com.co.sofka.back.DTO.CoordinatesDTO;
import com.co.sofka.back.Models.Coordinates;
import com.co.sofka.back.Services.CoordinatesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/coordinates")

public class CoordinatesController {

    @Autowired
    CoordinatesServices coordinatesServices;

    @GetMapping()
    public ResponseEntity<CoordinatesDTO> getCoordinates(){
        return new ResponseEntity(generateCoordinates(), HttpStatus.OK);
    }

    public CoordinatesDTO generateCoordinates(){
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        String pattern = "yyyy-mm-dd";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
        coordinatesDTO.setDate(simpleDateFormat.format(new Date()));
        coordinatesDTO.setLatitud(generateLatitud());
        coordinatesDTO.setLongitud(generateLongitud());

        coordinatesServices.crear(coordinatesDTO);

        return coordinatesDTO;
    }

    public String generateLatitud(){
        //MÁXIMO: 10.1268506309 y como MÍNIMO: -34.694488825

        Double max = 10.1268506309;
        Double min = -34.694488825;
        Random rdn = new Random();

        String latitud = String.valueOf(min +(max - min) * rdn.nextDouble());

         return latitud;
    }

    public String generateLongitud(){
        //máx: 32.4047333991 y mín: -0.0883464465
        Double max = 32.4047333991;
        Double min = -0.0883464465;
        Random rdn = new Random();

        String longitud = String.valueOf(min +(max - min) * rdn.nextDouble());
        return longitud;
    }
}
