package com.example.codingexercisesmartequip;

import com.example.codingexercisesmartequip.model.request.AnswerRequest;
import com.example.codingexercisesmartequip.model.response.QuestionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class SumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGenerateQuestion() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkForCorrectAnswer() throws Exception {
        QuestionResponse questionResponse = new QuestionResponse("1", "Please sum the numbers 5,7", List.of(5, 7));
        AnswerRequest answerRequest = new AnswerRequest("1", "Please sum the numbers 5,7", 12);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"questionId\":\"1\",\"question\":\"Please sum the numbers 5,7\",\"sum\":12}"))
                .andExpect(status().isOk())
                .andExpect(content().string("That’s great"));
    }

    @Test
    void shouldReturnBadRequestForIncorrectAnswer() throws Exception {
        QuestionResponse questionResponse = new QuestionResponse("1", "Please sum the numbers 5,7", List.of(5, 7));
        AnswerRequest answerRequest = new AnswerRequest("1", "Please sum the numbers 5,7", 10);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"questionId\":\"1\",\"question\":\"Please sum the numbers 5,7\",\"sum\":10}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("That’s wrong. Please try again."));
    }
}
