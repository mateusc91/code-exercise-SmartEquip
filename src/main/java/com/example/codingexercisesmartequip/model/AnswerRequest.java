package com.example.codingexercisesmartequip.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnswerRequest {
    private String questionId;
    private String question;
    private int sum;
}
