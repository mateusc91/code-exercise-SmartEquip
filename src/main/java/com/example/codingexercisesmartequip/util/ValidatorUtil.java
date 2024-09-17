package com.example.codingexercisesmartequip.util;

import com.example.codingexercisesmartequip.model.request.AnswerRequest;
import com.example.codingexercisesmartequip.model.response.QuestionResponse;

public class ValidatorUtil {
    public static boolean isCorrectAnswer(QuestionResponse questionResponse, AnswerRequest answerRequest) {
        if (questionResponse == null) {
            return false;
        }

        // Calculate the correct sum
        int correctSum = questionResponse.getNumbers().stream().mapToInt(Integer::intValue).sum();

        // Check if the provided sum matches the correct sum
        return answerRequest.getSum() == correctSum;
    }
}
