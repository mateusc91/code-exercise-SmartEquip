package com.example.codingexercisesmartequip.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnswerRequest {
    private String questionId;
    private String question;
    private int sum;
}
