package com.noom.interview.fullstack.sleep.log;

import com.noom.interview.fullstack.sleep.log.model.SleepLogRequest;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.log.service.CreateSleepLogService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sleep-log")
public class SleepLogController {

  private final CreateSleepLogService createSleepLogService;

  public SleepLogController(CreateSleepLogService createSleepLogService) {
    this.createSleepLogService = createSleepLogService;
  }

  @PostMapping
  public ResponseEntity<SleepLogResponse> logLastNightSleep(
      @Valid @RequestBody SleepLogRequest request, @RequestParam("userId") Long userId) {
    SleepLogResponse response = createSleepLogService.createLastNightSleepLog(request, userId);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
