package com.noom.interview.fullstack.sleep.log.service;

import com.noom.interview.fullstack.sleep.log.model.SleepLogRequest;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;

public interface CreateSleepLogService {
  SleepLogResponse createLastNightSleepLog(SleepLogRequest sleepLog, Long userId);
}
