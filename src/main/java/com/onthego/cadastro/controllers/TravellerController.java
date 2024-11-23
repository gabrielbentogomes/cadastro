package com.onthego.cadastro.controllers;

import com.onthego.cadastro.entities.traveller.Traveller;
import com.onthego.cadastro.entities.traveller.TravellerDTO;
import com.onthego.cadastro.services.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "travellers")
public class TravellerController {

    @Autowired
    public TravellerService travellerService;


    @GetMapping
    public ResponseEntity<?> getAllTravellers() {
        List<Traveller> travellers = travellerService.findAll();
        List<TravellerDTO> travellersDTO = travellers.stream()
                .map(traveller -> new TravellerDTO(traveller.getName(), traveller.getEmail()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(travellersDTO);
    }

}
