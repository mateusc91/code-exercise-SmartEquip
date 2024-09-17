package com.example.codingexercisesmartequip;

import com.example.codingexercisesmartequip.model.request.AnswerRequest;
import com.example.codingexercisesmartequip.model.response.QuestionResponse;
import com.example.codingexercisesmartequip.service.SumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class SumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SumService sumService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGenerateSumQuestion() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnOkForCorrectAnswer() throws Exception {
        MvcResult result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        QuestionResponse questionResponse = objectMapper.readValue(content, QuestionResponse.class);
        int correctSum = questionResponse.getNumbers().stream().mapToInt(Integer::intValue).sum();

        AnswerRequest answerRequest = new AnswerRequest(
                questionResponse.getQuestionId(),
                questionResponse.getQuestion(),
                correctSum
        );

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"That’s great\"}"));

        assertThat(answerRequest.getSum()).isEqualTo(correctSum);
        assertThat(answerRequest.getQuestionId()).isEqualTo(questionResponse.getQuestionId());
        assertThat(answerRequest.getQuestion()).isEqualTo(questionResponse.getQuestion());
    }

    @Test
    void shouldReturnBadRequestForIncorrectSumAnswer() throws Exception {
        QuestionResponse questionResponse = sumService.buildQuestion();
        AnswerRequest answerRequest = new AnswerRequest(questionResponse.getQuestionId(), questionResponse.getQuestion(), 999);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Invalid sum provided for the question.\"}"));
    }
}
