package com.noom.interview.fullstack.sleep.log;

import com.noom.interview.fullstack.sleep.log.model.SleepLogRequest;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.log.model.SleepLogSummaryResponse;
import com.noom.interview.fullstack.sleep.log.service.CreateSleepLogService;
import com.noom.interview.fullstack.sleep.log.service.FindSleepLogService;
import com.noom.interview.fullstack.sleep.log.service.SleepLogSummaryService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sleep-log")
@RequiredArgsConstructor
public class SleepLogController {

  private final CreateSleepLogService createSleepLogService;
  private final FindSleepLogService findSleepLogService;
  private final SleepLogSummaryService sleepLogSummaryService;

  @PostMapping
  public ResponseEntity<SleepLogResponse> logLastNightSleep(
      @Valid @RequestBody SleepLogRequest request, @RequestParam("userId") Long userId) {
    SleepLogResponse response = createSleepLogService.createLastNightSleepLog(request, userId);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/last-night")
  public ResponseEntity<SleepLogResponse> findLastNightSleepLog(
      @RequestParam("userId") Long userId) {
    SleepLogResponse response = findSleepLogService.findLastNightSleepLog(userId);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/summary")
  public ResponseEntity<SleepLogSummaryResponse> getLastNDaysSummary(
      @RequestParam("userId") Long userId, @RequestParam("days") Integer days) {
    SleepLogSummaryResponse response = sleepLogSummaryService.getLastNDaysSummary(userId, days);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
