package com.example.codingexercisesmartequip.service;
import com.example.codingexercisesmartequip.model.AnswerRequest;
import com.example.codingexercisesmartequip.model.QuestionResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class SumService {

    public QuestionResponse generateQuestion() {
        Random random = new Random();
        int numCount = random.nextInt(3) + 2;  // Generate between 2 to 4 numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < numCount; i++) {
            numbers.add(random.nextInt(10) + 1);  // Numbers between 1 and 10
        }
        String questionId = UUID.randomUUID().toString();
        String question = "Please sum the numbers " + String.join(",", numbers.toString().replace("[", "").replace("]", ""));
        return new QuestionResponse(questionId, question, numbers);
    }

    public boolean validateAnswer(AnswerRequest answerRequest, QuestionResponse storedQuestion) {
        int correctSum = storedQuestion.getNumbers().stream().mapToInt(Integer::intValue).sum();
        return answerRequest.getSum() == correctSum && storedQuestion.getQuestionId().equals(answerRequest.getQuestionId());
    }
}
