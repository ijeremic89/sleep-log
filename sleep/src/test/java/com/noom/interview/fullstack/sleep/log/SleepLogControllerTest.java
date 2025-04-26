package com.noom.interview.fullstack.sleep.log;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.noom.interview.fullstack.sleep.log.model.Mood;
import com.noom.interview.fullstack.sleep.log.model.SleepLogRequest;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.log.model.SleepLogSummaryResponse;
import com.noom.interview.fullstack.sleep.log.service.CreateSleepLogService;
import com.noom.interview.fullstack.sleep.log.service.FindSleepLogService;
import com.noom.interview.fullstack.sleep.log.service.SleepLogSummaryService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SleepLogController.class)
class SleepLogControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private CreateSleepLogService createSleepLogService;

  @MockBean private FindSleepLogService findSleepLogService;

  @MockBean private SleepLogSummaryService sleepLogSummaryService;

  @Test
  void logLastNightSleep_returnsCreatedSleepLog() throws Exception {
    SleepLogRequest request =
        new SleepLogRequest(LocalTime.of(22, 0), LocalTime.of(6, 0), Mood.GOOD);
    SleepLogResponse response =
        new SleepLogResponse(
            1L, LocalDate.now(), LocalTime.of(22, 0), LocalTime.of(6, 0), 15000L, Mood.GOOD);

    when(createSleepLogService.createLastNightSleepLog(any(SleepLogRequest.class), eq(1L)))
        .thenReturn(response);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());

    mockMvc
        .perform(
            post("/api/v1/sleep-log")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .param("userId", "1"))
        .andExpect(status().isCreated());
  }

  @Test
  void findLastNightSleepLog_returnsLastNightSleepLog() throws Exception {
    SleepLogResponse response =
        new SleepLogResponse(
            1L, LocalDate.now(), LocalTime.of(22, 0), LocalTime.of(6, 0), 15000L, Mood.GOOD);

    when(findSleepLogService.findLastNightSleepLog(1L)).thenReturn(response);

    mockMvc
        .perform(get("/api/v1/sleep-log/last-night").param("userId", "1"))
        .andExpect(status().isOk());
  }

  @Test
  void getLastNDaysSummary_returnsSummary() throws Exception {
    SleepLogSummaryResponse summaryResponse =
        new SleepLogSummaryResponse(
            LocalDate.of(2025, 4, 10),
            LocalDate.of(2025, 4, 1),
            14588L,
            LocalTime.of(22, 0),
            LocalTime.of(6, 0),
            Map.of(Mood.GOOD, 2L, Mood.OK, 1L, Mood.BAD, 0L));

    when(sleepLogSummaryService.getLastNDaysSummary(1L, 5)).thenReturn(summaryResponse);

    mockMvc
        .perform(get("/api/v1/sleep-log/summary").param("userId", "1").param("days", "5"))
        .andExpect(status().isOk());
  }
}
