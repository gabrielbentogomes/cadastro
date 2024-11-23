package com.onthego.cadastro.entities.traveller;

import java.util.UUID;

public record LoginResponseDTO(String token, String nome, String email, UUID userId) {
}
