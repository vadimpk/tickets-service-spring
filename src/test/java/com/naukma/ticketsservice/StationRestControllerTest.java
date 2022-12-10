package com.naukma.ticketsservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naukma.ticketsservice.station.Station;
import com.naukma.ticketsservice.station.StationController;
import com.naukma.ticketsservice.station.StationDto;
import com.naukma.ticketsservice.station.StationService;
import com.naukma.ticketsservice.user.UserPrincipalDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StationController.class)
public class StationRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UserPrincipalDetailsService userPrincipalDetailsService;
  @MockBean
  private StationController stationController;
  @MockBean
  private StationService stationService;

  @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
  @Test
  public void whenValidInput_thenReturns200() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders
            .post("/api/v1/station")
            .accept(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"new Station Test\"}")
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();
  }

  @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
  @Test
  void whenDeleteNonExistingWagon_thenReturns404() throws Exception {
    mockMvc.perform(delete("/wagon/{id}", 256))
            .andExpect(status().is(404));
  }


  @Test
  @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
  void whenValidInput_thenReturnsValidWagonDto() throws Exception {
    StationDto stationDto = new StationDto("Second test", null);
    mockMvc.perform(post("/api/v1/wagon")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(stationDto))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    stationService.createStation(new Station(stationDto.getName()));
    Optional<Station> station = stationService.findByName(stationDto.getName());

    if (station.isEmpty()) throw new RuntimeException("station with name = " + stationDto.getName() + " is supposed to be found");

    MvcResult mvcResult = mockMvc.perform(get("/api/v1/station/{id}", station.get().getId()))
            .andExpect(status().isOk())
            .andReturn();

    String actualResponseBody = mvcResult.getResponse().getContentAsString();
    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
            objectMapper.writeValueAsString(stationDto));
  }
}
