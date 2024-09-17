package com.example.codingexercisesmartequip.controller;

import com.example.codingexercisesmartequip.model.AnswerRequest;
import com.example.codingexercisesmartequip.model.QuestionResponse;
import com.example.codingexercisesmartequip.service.SumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class SumController {

    private final SumService sumService;
    private final Map<String, QuestionResponse> questionStore = new HashMap<>();

    public SumController(SumService sumService) {
        this.sumService = sumService;
    }

    @GetMapping
    public ResponseEntity<QuestionResponse> getQuestion() {
        QuestionResponse questionResponse = sumService.generateQuestion();
        questionStore.put(questionResponse.getQuestionId(), questionResponse);
        return new ResponseEntity<>(questionResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> submitAnswer(@RequestBody AnswerRequest answerRequest) {
        QuestionResponse storedQuestion = questionStore.get(answerRequest.getQuestionId());
        if (storedQuestion != null && sumService.validateAnswer(answerRequest, storedQuestion)) {
            return ResponseEntity.ok("That’s great");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("That’s wrong. Please try again.");
        }
    }
}
