package com.noom.interview.fullstack.sleep.log.service.impl;

import com.noom.interview.fullstack.sleep.common.exception.SleepLogNotFoundException;
import com.noom.interview.fullstack.sleep.log.SleepLogRepository;
import com.noom.interview.fullstack.sleep.log.mapper.SleepLogMapper;
import com.noom.interview.fullstack.sleep.log.model.SleepLogEntity;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.log.service.FindSleepLogService;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindSleepLogServiceImpl implements FindSleepLogService {

  private final SleepLogRepository sleepLogRepository;

  @Override
  public SleepLogResponse findLastNightSleepLog(Long userId) {
    LocalDate lastNightSleepLogDate = LocalDate.now();

    return sleepLogRepository
        .findByUserIdAndFromDate(userId, lastNightSleepLogDate)
        .map(SleepLogMapper.INSTANCE::toResponse)
        .orElseThrow(() -> new SleepLogNotFoundException(userId, lastNightSleepLogDate));
  }
}
