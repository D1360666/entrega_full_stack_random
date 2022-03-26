package com.co.sofka.back.Services;

import com.co.sofka.back.DTO.CoordinatesDTO;
import com.co.sofka.back.Mappers.CoordinatesMapper;
import com.co.sofka.back.Models.Coordinates;
import com.co.sofka.back.Repository.CoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatesServices {
    @Autowired
    CoordinatesRepository coordinatesRepository;

    CoordinatesMapper mapper = new CoordinatesMapper();

    public List<CoordinatesDTO> obtenerTodos(){
        List<Coordinates>coordinates = (List<Coordinates>) coordinatesRepository.findAll();
        return mapper.fromCollectionList(coordinates);
    }

    public CoordinatesDTO obtenerPorId(String id){
        Coordinates coordinates = coordinatesRepository.findById(id).orElseThrow(()-> new RuntimeException("Las coordenadas no existen"));
        return mapper.fromCollection(coordinates);
    }

    public CoordinatesDTO crear(CoordinatesDTO coordinatesDTO){
        Coordinates coordinates = mapper.fromDTO(coordinatesDTO);
        return mapper.fromCollection(coordinatesRepository.save(coordinates));
    }

    public CoordinatesDTO modificar(CoordinatesDTO coordinatesDTO){
        Coordinates coordinates = mapper.fromDTO(coordinatesDTO);
        coordinatesRepository.findById(coordinatesDTO.getId()).orElseThrow(()-> new RuntimeException("Empleado no encontrado"));
        return mapper.fromCollection(coordinatesRepository.save(coordinates));
    }

}
