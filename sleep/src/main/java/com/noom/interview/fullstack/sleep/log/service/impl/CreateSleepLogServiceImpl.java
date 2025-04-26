package com.noom.interview.fullstack.sleep.log.service.impl;

import com.noom.interview.fullstack.sleep.common.exception.SleepLogAlreadyExistsException;
import com.noom.interview.fullstack.sleep.common.exception.UserNotFoundException;
import com.noom.interview.fullstack.sleep.log.SleepLogRepository;
import com.noom.interview.fullstack.sleep.log.mapper.SleepLogMapper;
import com.noom.interview.fullstack.sleep.log.model.SleepLogEntity;
import com.noom.interview.fullstack.sleep.log.model.SleepLogRequest;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.log.service.CreateSleepLogService;
import com.noom.interview.fullstack.sleep.user.UserEntity;
import com.noom.interview.fullstack.sleep.user.UserRepository;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSleepLogServiceImpl implements CreateSleepLogService {

  private final SleepLogRepository sleepLogRepository;
  private final UserRepository userRepository;

  @Override
  public SleepLogResponse createLastNightSleepLog(SleepLogRequest request, Long userId) {
    LocalDate sleepDate = LocalDate.now();
    UserEntity user =
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    sleepLogRepository
        .findByUserIdAndFromDate(userId, sleepDate)
        .ifPresent(
            sleep -> {
              throw new SleepLogAlreadyExistsException(userId, sleepDate);
            });

    SleepLogEntity sleepLog = SleepLogMapper.INSTANCE.toEntity(request);
    sleepLog.setUser(user);
    sleepLog.setSleepDate(sleepDate);
    sleepLog.setTotalTimeInBed(
        calculateTotalTimeInBed(request.timeInBedFrom(), request.timeInBedTo()));
    sleepLogRepository.save(sleepLog);

    return SleepLogMapper.INSTANCE.toResponse(sleepLog);
  }

  private Duration calculateTotalTimeInBed(LocalTime from, LocalTime to) {
    if (from == null || to == null) {
      throw new IllegalArgumentException("Times cannot be null");
    }

    return from.isBefore(to)
        ? Duration.between(from, to)
        : Duration.ofHours(24).minus(Duration.between(to, from));
  }
}
