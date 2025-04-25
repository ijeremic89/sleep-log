package com.noom.interview.fullstack.sleep.log.service.impl;

import com.noom.interview.fullstack.sleep.log.SleepLogRepository;
import com.noom.interview.fullstack.sleep.log.model.SleepLogRequest;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.log.service.CreateSleepLogService;
import org.springframework.stereotype.Service;

@Service
public class CreateSleepLogServiceImpl implements CreateSleepLogService {

  private final SleepLogRepository sleepLogRepository;

  public CreateSleepLogServiceImpl(SleepLogRepository sleepLogRepository) {
    this.sleepLogRepository = sleepLogRepository;
  }

  @Override
  public SleepLogResponse createLastNightSleepLog(SleepLogRequest sleepLog, Long userId) {
    return null;
  }
}
