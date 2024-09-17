package com.example.codingexercisesmartequip.controller;

import com.example.codingexercisesmartequip.exception.InvalidSumException;
import com.example.codingexercisesmartequip.model.request.AnswerRequest;
import com.example.codingexercisesmartequip.model.response.QuestionResponse;
import com.example.codingexercisesmartequip.service.SumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@Tag(name = "Sum Service API", description = "API for generating sum questions and validating answers.")
public class SumController {

    private final SumService sumService;

    public SumController(SumService sumService) {
        this.sumService = sumService;
    }

    @Operation(summary = "Generate a sum question with random numbers")
    @GetMapping
    public ResponseEntity<QuestionResponse> getQuestion() {
        return ResponseEntity.ok(sumService.generateQuestion());
    }

    @Operation(summary = "Submit an answer to a previously generated sum question")
    @PostMapping
    public ResponseEntity<String> submitAnswer(@RequestBody AnswerRequest answerRequest) {
        // The service handles the answer validation
        sumService.validateAnswer(answerRequest);
        return ResponseEntity.ok("That’s great");
    }
}
