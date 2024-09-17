package com.example.codingexercisesmartequip.service;
import com.example.codingexercisesmartequip.exception.InvalidSumException;
import com.example.codingexercisesmartequip.model.request.AnswerRequest;
import com.example.codingexercisesmartequip.dto.AnswerResponseDTO;
import com.example.codingexercisesmartequip.model.response.QuestionResponse;
import com.example.codingexercisesmartequip.util.ValidatorUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SumService {
    private final Map<String, QuestionResponse> questionStored = new HashMap<>();

    public QuestionResponse buildQuestion() {
        List<Integer> numbers = generateRandomNumbers();
        String questionString = generateQuestionResponse(numbers);

        QuestionResponse questionResponse = QuestionResponse.builder()
                .question(questionString)
                .numbers(numbers)
                .build();

        questionStored.put(questionResponse.getQuestionId(), questionResponse);
        return questionResponse;
    }
    private List<Integer> generateRandomNumbers() {
        Random random = new Random();
        int numCount = random.nextInt(9) + 1;

        return IntStream.range(0, numCount)
                .map(i -> random.nextInt(10) + 1)
                .boxed()
                .collect(Collectors.toList());
    }
    private String generateQuestionResponse(List<Integer> numbers) {
        return "Please sum the following numbers (if only one number is generated, the sum will be its own value): " + numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public AnswerResponseDTO validateSumCalculation(AnswerRequest answerRequest) {
        QuestionResponse storedQuestion = questionStored.get(answerRequest.getQuestionId());
        if (!ValidatorUtil.isCorrectAnswer(storedQuestion, answerRequest)) {
            throw new InvalidSumException("Invalid sum provided for the question");
        }
        return new AnswerResponseDTO("That’s great");
    }
}
