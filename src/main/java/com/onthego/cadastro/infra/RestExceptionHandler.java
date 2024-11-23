package com.onthego.cadastro.infra;

import com.onthego.cadastro.exceptions.EmailAlreadyExists;
import com.onthego.cadastro.exceptions.IdNotExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailAlreadyExists.class)
    private ResponseEntity<String> emailAlreadyExistsResponseEntity(EmailAlreadyExists exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email exists");
    }

    @ExceptionHandler(IdNotExists.class)
    private ResponseEntity<String> idNotExists(IdNotExists exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not exists");
    }
}
