package com.onthego.cadastro.controllers;

import com.onthego.cadastro.entities.trip.Trip;
import com.onthego.cadastro.repositories.TripRepository;
import com.onthego.cadastro.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private TripRepository tripRepository;

    @PostMapping
    public ResponseEntity<?> saveTrip(@RequestBody Trip trip) {
        Trip t1 = tripService.saveTrip(trip);
        return ResponseEntity.ok("Viagem cadastrada com sucesso!");
    }
    @GetMapping
    public ResponseEntity<?> getAllTrips() {
        return ResponseEntity.ok(tripService.findAll());
    }

    @GetMapping("/{travellerId}")
    public ResponseEntity<List<Trip>> getTripsByTravellerId(@PathVariable UUID travellerId) {
        List<Trip> trips = tripService.findByTravellerId(travellerId);

        return ResponseEntity.ok(trips); // Retorna 200 e a lista de viagens
    }

    @GetMapping("by-tripid/{tripId}")
    public ResponseEntity<?> getTripById(@PathVariable UUID tripId) {
        Trip trip = tripService.findById(tripId);

        return ResponseEntity.ok(trip);
    }
}
