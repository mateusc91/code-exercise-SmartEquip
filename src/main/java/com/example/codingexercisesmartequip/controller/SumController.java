package com.example.codingexercisesmartequip.controller;

import com.example.codingexercisesmartequip.model.request.AnswerRequest;
import com.example.codingexercisesmartequip.dto.AnswerResponseDTO;
import com.example.codingexercisesmartequip.model.response.QuestionResponse;
import com.example.codingexercisesmartequip.service.SumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<QuestionResponse> generateSumQuestion() {
        return ResponseEntity.ok(sumService.buildQuestion());
    }

    @Operation(summary = "Submit an answer to a previously generated sum question")
    @PostMapping
    public ResponseEntity<AnswerResponseDTO> generateSumAnswer(@RequestBody AnswerRequest answerRequest) {
        return ResponseEntity.ok(sumService.buildAnswerSumCalculation(answerRequest));
    }
}
