package com.onthego.cadastro.repositories;

import com.onthego.cadastro.entities.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {

    List<Trip> findTripByTravellersId(UUID id);

    Trip findTripById(UUID id);
}
