package com.noom.interview.fullstack.sleep.log.service.impl;

import com.noom.interview.fullstack.sleep.common.exception.InvalidDaysException;
import com.noom.interview.fullstack.sleep.common.exception.UserNotFoundException;
import com.noom.interview.fullstack.sleep.log.SleepLogRepository;
import com.noom.interview.fullstack.sleep.log.mapper.SleepLogMapper;
import com.noom.interview.fullstack.sleep.log.model.SleepLogSummaryProjection;
import com.noom.interview.fullstack.sleep.log.model.SleepLogSummaryResponse;
import com.noom.interview.fullstack.sleep.log.service.SleepLogSummaryService;
import com.noom.interview.fullstack.sleep.user.UserRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SleepLogSummaryServiceImpl implements SleepLogSummaryService {

  private final SleepLogRepository sleepLogRepository;
  private final UserRepository userRepository;

  @Override
  public SleepLogSummaryResponse getLastNDaysSummary(Long userId, Integer days) {
    userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    if (days <= 0) {
      throw new InvalidDaysException("Days must be greater than 0.");
    }

    LocalDate toDate = LocalDate.now();
    LocalDate fromDate = toDate.minusDays(days);

    SleepLogSummaryProjection statistics =
        sleepLogRepository.getSleepSummary(userId, fromDate, toDate);

    if (statistics == null || statistics.getFromDate() == null || statistics.getToDate() == null) {
      return null;
    }

    return SleepLogMapper.INSTANCE.toSummaryResponse(statistics);
  }
}
