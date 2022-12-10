package com.naukma.ticketsservice;

import com.naukma.ticketsservice.train.Train;
import com.naukma.ticketsservice.train.TrainDto;
import com.naukma.ticketsservice.train.TrainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebFluxControllerTest {

    @Autowired
    private WebApplicationContext context;
    private static Long ID = 0L;

    @MockBean
    private WebTestClient webClient;

    @MockBean
    private TrainService trainService;

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void shouldGetTrain() {
        trainService.save(new Train("train", 56));
        ID = trainService.find("train").get().getId();

        webClient
                .get().uri("/api/v1/train/{id}", ID)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(TrainDto.class);
    }

    @Test
    @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
    public void shouldGetTrainFail() {
        webClient
                .get().uri("/api/v1/train/{id}", ID)
                .exchange()
                .expectStatus()
                .isEqualTo(404);
    }
}