package com.example.codingexercisesmartequip.exception;

import com.example.codingexercisesmartequip.dto.AnswerResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidSumException.class)
    public ResponseEntity<AnswerResponseDTO> handleInvalidSum(InvalidSumException ex) {
        AnswerResponseDTO response = new AnswerResponseDTO(ex.getLocalizedMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}