package com.noom.interview.fullstack.sleep.log;

import com.noom.interview.fullstack.sleep.log.model.SleepLogRequest;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.log.service.CreateSleepLogService;
import com.noom.interview.fullstack.sleep.log.service.FindSleepLogService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sleep-log")
public class SleepLogController {

  private final CreateSleepLogService createSleepLogService;
  private final FindSleepLogService findSleepLogService;

  public SleepLogController(
      CreateSleepLogService createSleepLogService, FindSleepLogService findSleepLogService) {
    this.createSleepLogService = createSleepLogService;
    this.findSleepLogService = findSleepLogService;
  }

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
}
