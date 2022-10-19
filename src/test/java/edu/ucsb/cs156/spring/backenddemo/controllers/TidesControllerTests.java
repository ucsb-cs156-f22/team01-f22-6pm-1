package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;


@WebMvcTest(value = TidesController.class)
public class TidesControllerTests {
  private ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  TidesQueryService mockTidesQueryService;


  @Test
  public void test_getTides() throws Exception {
  
    String fakeJsonResult="{ \"fake\" : \"result\" }";
    String beginDate = "20200101";
    String endDate = "20200102";
    String station = "9414290";
    when(mockTidesQueryService.getJSON(eq(beginDate),eq(endDate),eq(station))).thenReturn(fakeJsonResult);

    String url = String.format("/api.tidesandcurrents.noaa.gov/api/prod/datagetter?application=ucsb-cs156&begin_date=%s&end_date=%s&station=%s&product=predictions&datum=mllw&units=english&time_zone=lst_ldt&interval=hilo&format=json",beginDate,endDate,station);
    System.out.println("printing url");
    System.out.println(url);
    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();
    System.out.println("response retrieved");
    String responseString = response.getResponse().getContentAsString();
    System.out.println("printing response string");
    System.out.println(responseString);
    assertEquals(fakeJsonResult, responseString);
  }

}
