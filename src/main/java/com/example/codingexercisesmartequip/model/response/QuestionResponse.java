package com.example.codingexercisesmartequip.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
public class QuestionResponse {
    private String questionId;
    private String question;
    private List<Integer> numbers;
}
