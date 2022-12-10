package com.naukma.ticketsservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naukma.ticketsservice.user.UserPrincipalDetailsService;
import com.naukma.ticketsservice.wagon.WagonController;
import com.naukma.ticketsservice.wagon.WagonDto;
import com.naukma.ticketsservice.wagon.WagonService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WagonController.class)
public class WagonRestControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private UserPrincipalDetailsService userPrincipalDetailsService;
  @MockBean
  private WagonController wagonController;
  @MockBean
  private WagonService wagonService;

  @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
  @Test
  public void whenValidInput_thenReturns200() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders
            .post("/api/v1/wagon")
            .accept(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"wagon\"," +
                    "\"number_of_seats\": 45," +
                    "\"train_id\": 0}")
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();
  }


  @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
  @Test
  void whenInvalidInput_thenReturns415() throws Exception {
    WagonDto wagonDto = new WagonDto("", -50, -1L);

    mockMvc.perform(post("/api/v1/wagon")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(wagonDto))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(400));
  }

  @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
  @Test
  void whenDeleteNonExistingWagon_thenReturns404() throws Exception {
    mockMvc.perform(delete("/wagon/{id}", 256))
            .andExpect(status().is(404));
  }


//  @Test
//  @WithMockUser(username = "admin", password = "123", roles = "ADMIN")
//  void whenValidInput_thenReturnsValidWagonDto() throws Exception {
//    String  name = "train12";
//    WagonDto wagonDto = new WagonDto(name, 15, 0L);
//    mockMvc.perform(post("/api/v1/wagon")
//                    .accept(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(wagonDto))
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk());
//
//    wagonService.save(new Wagon(name, 15, null));
//
//    Wagon wagon = wagonService.getWagons().get(0);
//    if (wagon == null) throw new RuntimeException("wagon with name = " + name +" is supposed to be found");
//
//    MvcResult mvcResult = mockMvc.perform(get("/api/v1/wagon/{id}", 1))
//            .andExpect(status().isOk())
//            .andReturn();
//
//    String actualResponseBody = mvcResult.getResponse().getContentAsString();
//    assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
//            objectMapper.writeValueAsString(wagonDto));
//  }


}
