package com.co.sofka.back.Mappers;

import com.co.sofka.back.DTO.CoordinatesDTO;
import com.co.sofka.back.Models.Coordinates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoordinatesMapper {

    public Coordinates fromDTO(CoordinatesDTO dto){
        Coordinates coordinates = new Coordinates();
        coordinates.setId(dto.getId());
        coordinates.setLongitud(dto.getLongitud());
        coordinates.setLatitud(dto.getLatitud());
        coordinates.setDate(dto.getDate());

        return coordinates;
    }

    public CoordinatesDTO fromCollection(Coordinates collection){
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO();
        coordinatesDTO.setId(collection.getId());
        coordinatesDTO.setLongitud(collection.getLongitud());
        coordinatesDTO.setLatitud(collection.getLatitud());
        coordinatesDTO.setDate(collection.getDate());

        return coordinatesDTO;
    }

    public List<CoordinatesDTO> fromCollectionList(List<Coordinates> collection){
        if(collection == null){
            return null;
        }
        List<CoordinatesDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()){
            Coordinates coordinates = (Coordinates) listTracks.next();
            list.add((fromCollection(coordinates)));
        }
        return list;
    }
}
