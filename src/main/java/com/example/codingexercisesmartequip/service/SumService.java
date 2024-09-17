package com.example.codingexercisesmartequip.service;
import com.example.codingexercisesmartequip.exception.InvalidSumException;
import com.example.codingexercisesmartequip.model.request.AnswerRequest;
import com.example.codingexercisesmartequip.model.response.QuestionResponse;
import com.example.codingexercisesmartequip.util.ValidatorUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SumService {

    // Store the generated questions for validation
    private final Map<String, QuestionResponse> questionStore = new HashMap<>();

    public QuestionResponse generateQuestion() {
        Random random = new Random();
        int numCount = random.nextInt(3) + 2;  // Generate between 2 and 4 numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < numCount; i++) {
            numbers.add(random.nextInt(10) + 1);  // Numbers between 1 and 10
        }

        String questionId = UUID.randomUUID().toString();
        String question = "Please sum the numbers " + String.join(",", numbers.toString().replace("[", "").replace("]", ""));

        // Create and store the question
        QuestionResponse questionResponse = new QuestionResponse(questionId, question, numbers);
        questionStore.put(questionId, questionResponse);
        return questionResponse;
    }

    public void validateAnswer(AnswerRequest answerRequest) {
        QuestionResponse storedQuestion = questionStore.get(answerRequest.getQuestionId());
        if (!ValidatorUtil.isCorrectAnswer(storedQuestion, answerRequest)) {
            throw new InvalidSumException("Invalid sum provided for the question");
        }
    }
}
