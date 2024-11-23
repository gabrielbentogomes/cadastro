package com.onthego.cadastro.services;

import com.onthego.cadastro.entities.traveller.Traveller;
import com.onthego.cadastro.exceptions.EmailAlreadyExists;
import com.onthego.cadastro.repositories.TravellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerService {

    @Autowired
    public TravellerRepository travellerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Traveller save(Traveller traveller) {
        Traveller existingTraveller = findByEmail(traveller.getEmail());
        if (existingTraveller != null) {
            throw new EmailAlreadyExists();
        }
        String encodedPassword = passwordEncoder.encode(traveller.getPassword());
        traveller.setPassword(encodedPassword);
        return travellerRepository.save(traveller);
    }

    public List<Traveller> findAll() {
        List<Traveller> travellers = travellerRepository.findAll();
        return travellerRepository.findAll();
    }

    public Traveller findByEmail(String email) {
        return travellerRepository.findByEmail(email);
    }
}
