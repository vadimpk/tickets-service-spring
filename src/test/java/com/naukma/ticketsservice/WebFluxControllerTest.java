package com.naukma.ticketsservice;

import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.train.TrainController;
import com.naukma.ticketsservice.train.TrainDto;
import com.naukma.ticketsservice.train.TrainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(TrainController.class)
public class WebFluxControllerTest {

    private static Long ID = 0L;

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private TrainService trainService;

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void shouldGetUser() {
        trainService.createTrain(new Train("train", 56));
        ID = trainService.findTrainByName("train").get().getId();

        webClient
                .get().uri("/api/v1/train/{id}", ID)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(TrainDto.class);
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void shouldGetUserFail() {

        when(trainService.findTrain(ID + 1L)).thenThrow(new RuntimeException("user with id =" + (ID + 1)+  " is supposed not ti be found"));

        webClient
                .get().uri("/api/v1/train/{id}", ID)
                .exchange()
                .expectStatus()
                .isEqualTo(404);
    }
}