package com.onthego.cadastro.services;

import com.onthego.cadastro.entities.traveller.Traveller;
import com.onthego.cadastro.entities.trip.Trip;
import com.onthego.cadastro.exceptions.IdNotExists;
import com.onthego.cadastro.exceptions.TripNotFound;
import com.onthego.cadastro.repositories.TravellerRepository;
import com.onthego.cadastro.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    public TravellerRepository travellerRepository;

    public Trip saveTrip(Trip trip) {
        for (Traveller traveller : trip.getTravellers()) {
            Optional<Traveller> existingTraveller = travellerRepository.findById(traveller.getId());

            if (existingTraveller.isEmpty()) {
                throw new IdNotExists();
            }
        }

        return tripRepository.save(trip);
    }

    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    public Trip findById(UUID id) {
        Optional<Trip> trip = tripRepository.findById(id);

        return trip.orElseThrow(() -> new IdNotExists());
    }

    public List<Trip> findByTravellerId(UUID travellerId) {
        List<Trip> trips = tripRepository.findTripByTravellersId(travellerId);
        if (trips.isEmpty()) {
            throw new TripNotFound();
        }

        return trips;
    }

}
