package com.example.codingexercisesmartequip.util;

import com.example.codingexercisesmartequip.model.request.AnswerRequest;
import com.example.codingexercisesmartequip.model.response.QuestionResponse;

public class ValidatorUtil {
    public static boolean isCorrectAnswer(QuestionResponse questionResponse, AnswerRequest answerRequest) {
        if (questionResponse == null) {
            return false;
        }
        int correctSum = questionResponse.getNumbers().stream().mapToInt(Integer::intValue).sum();

        return answerRequest.getSum() == correctSum;
    }
}
