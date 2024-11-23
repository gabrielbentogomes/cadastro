package com.onthego.cadastro.repositories;

import com.onthego.cadastro.entities.traveller.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TravellerRepository extends JpaRepository<Traveller, UUID> {
    Traveller findByEmail(String email);

    Optional<Traveller> findById(UUID id);

}
