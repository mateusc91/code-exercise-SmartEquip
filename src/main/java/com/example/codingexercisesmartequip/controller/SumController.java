package com.example.codingexercisesmartequip.controller;

import com.example.codingexercisesmartequip.model.AnswerRequest;
import com.example.codingexercisesmartequip.model.QuestionResponse;
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
    private final Map<String, QuestionResponse> questionStore = new HashMap<>();

    public SumController(SumService sumService) {
        this.sumService = sumService;
    }

    @GetMapping
    @Operation(summary = "Generate a sum question with random numbers")
    public ResponseEntity<QuestionResponse> getQuestion() {
        QuestionResponse questionResponse = sumService.generateQuestion();
        questionStore.put(questionResponse.getQuestionId(), questionResponse);
        return new ResponseEntity<>(questionResponse, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Submit an answer to a previously generated sum question")
    public ResponseEntity<String> submitAnswer(@RequestBody AnswerRequest answerRequest) {
        QuestionResponse storedQuestion = questionStore.get(answerRequest.getQuestionId());
        if (storedQuestion != null && sumService.validateAnswer(answerRequest, storedQuestion)) {
            return ResponseEntity.ok("That’s great");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("That’s wrong. Please try again.");
        }
    }
}
