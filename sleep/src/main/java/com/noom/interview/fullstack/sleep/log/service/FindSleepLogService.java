package com.noom.interview.fullstack.sleep.log.service;

import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;

public interface FindSleepLogService {
  SleepLogResponse findLastNightSleepLog(Long userId);
}
