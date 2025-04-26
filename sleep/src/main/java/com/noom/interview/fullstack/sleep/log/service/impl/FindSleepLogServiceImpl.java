package com.noom.interview.fullstack.sleep.log.service.impl;

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
    Optional<SleepLogEntity> sleepLogO =
        sleepLogRepository.findByUserIdAndFromDate(userId, LocalDate.now().minusDays(1));

    return sleepLogO.map(SleepLogMapper.INSTANCE::toResponse).orElse(null);
  }
}
