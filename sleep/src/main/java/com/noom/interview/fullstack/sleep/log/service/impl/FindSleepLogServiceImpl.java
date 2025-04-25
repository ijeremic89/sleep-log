package com.noom.interview.fullstack.sleep.log.service.impl;

import com.noom.interview.fullstack.sleep.log.SleepLogRepository;
import com.noom.interview.fullstack.sleep.log.mapper.SleepLogMapper;
import com.noom.interview.fullstack.sleep.log.model.SleepLogEntity;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.log.service.FindSleepLogService;
import com.noom.interview.fullstack.sleep.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FindSleepLogServiceImpl implements FindSleepLogService {

  private final SleepLogRepository sleepLogRepository;

  public FindSleepLogServiceImpl(
      SleepLogRepository sleepLogRepository, UserRepository userRepository) {
    this.sleepLogRepository = sleepLogRepository;
  }

  @Override
  public SleepLogResponse findLastNightSleepLog(Long userId) {
    Optional<SleepLogEntity> sleepLogO =
        sleepLogRepository.findByUserIdAndFromDate(userId, LocalDate.now().minusDays(1));

    return sleepLogO.map(SleepLogMapper.INSTANCE::toResponse).orElse(null);
  }
}
