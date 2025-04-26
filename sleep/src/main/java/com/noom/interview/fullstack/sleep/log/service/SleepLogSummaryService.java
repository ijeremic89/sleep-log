package com.noom.interview.fullstack.sleep.log.service;

import com.noom.interview.fullstack.sleep.log.model.SleepLogSummaryResponse;

public interface SleepLogSummaryService {
  SleepLogSummaryResponse getLastNDaysSummary(Long userId, Integer days);
}
