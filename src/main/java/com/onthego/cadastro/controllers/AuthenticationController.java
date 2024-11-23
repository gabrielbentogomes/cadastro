package com.onthego.cadastro.controllers;

import com.onthego.cadastro.entities.traveller.AuthenticationDTO;
import com.onthego.cadastro.entities.traveller.LoginResponseDTO;
import com.onthego.cadastro.entities.traveller.RegisterDTO;
import com.onthego.cadastro.entities.traveller.Traveller;
import com.onthego.cadastro.infra.security.TokenService;
import com.onthego.cadastro.repositories.TravellerRepository;
import com.onthego.cadastro.services.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.onthego.cadastro.entities.enums.UserRole.USER;

@RestController
@RequestMapping(value = "auth")
@CrossOrigin // Permite CORS para este controller

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TravellerRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    public TravellerService travellerService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var traveller = (Traveller) auth.getPrincipal();
            var token = tokenService.generateToken((Traveller) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token, traveller.getName(), traveller.getEmail(), traveller.getId()));
        }catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Usuário ou senha inválidos."); // Mensagem para email ou senha incorretos
        } catch (AuthenticationCredentialsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciais não fornecidas."); // Mensagem para credenciais não fornecidas
        } catch (Exception e) {
            // Para outros tipos de exceções
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro durante a autenticação.");
        }

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        System.out.println("Data recebida: " + data);
        Traveller newUser = new Traveller(data.name(), data.email(), data.password(), USER);
        travellerService.save(newUser);
        return ResponseEntity.ok().body("Viajante salvo com sucesso");
    }



}
