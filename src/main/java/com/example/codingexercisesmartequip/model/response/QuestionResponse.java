package com.example.codingexercisesmartequip.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class QuestionResponse {

    @Builder.Default
    private String questionId = UUID.randomUUID().toString();
    private String question;
    private List<Integer> numbers;
}
