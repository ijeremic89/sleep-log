package com.noom.interview.fullstack.sleep.log.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.noom.interview.fullstack.sleep.common.exception.SleepLogNotFoundException;
import com.noom.interview.fullstack.sleep.log.SleepLogRepository;
import com.noom.interview.fullstack.sleep.log.model.Mood;
import com.noom.interview.fullstack.sleep.log.model.SleepLogEntity;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.user.UserEntity;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindSleepLogServiceImplTest {

  @Mock private SleepLogRepository sleepLogRepository;

  @InjectMocks private FindSleepLogServiceImpl findSleepLogService;

  private SleepLogEntity mockSleepLogEntity;

  @BeforeEach
  void setUp() {
    UserEntity mockUser = new UserEntity();
    mockUser.setId(1L);

    mockSleepLogEntity = new SleepLogEntity();
    mockSleepLogEntity.setUser(mockUser);
    mockSleepLogEntity.setSleepDate(LocalDate.now().minusDays(1));
    mockSleepLogEntity.setTimeInBedFrom(LocalTime.of(22, 0));
    mockSleepLogEntity.setTimeInBedTo(LocalTime.of(6, 0));
    mockSleepLogEntity.setTotalTimeInBed(Duration.ofHours(8));
    mockSleepLogEntity.setMood(Mood.GOOD);
  }

  @Test
  void findLastNightSleepLog_withExistingSleepLog_returnsSuccess() {
    // Given
    when(sleepLogRepository.findByUserIdAndFromDate(1L, LocalDate.now()))
        .thenReturn(Optional.of(mockSleepLogEntity));

    // When
    SleepLogResponse response = findSleepLogService.findLastNightSleepLog(1L);

    // Then
    assertNotNull(response);
    assertEquals(mockSleepLogEntity.getSleepDate(), response.sleepDate());
    assertEquals(mockSleepLogEntity.getTimeInBedFrom(), response.timeInBedFrom());
    assertEquals(mockSleepLogEntity.getTimeInBedTo(), response.timeInBedTo());
    assertEquals(
        mockSleepLogEntity.getTotalTimeInBed().getSeconds(), response.totalTimeInBedInSeconds());
    assertEquals(mockSleepLogEntity.getMood(), response.mood());
  }

  @Test
  void findLastNightSleepLog_withNonExistingSleepLog_throwsSleepLogNotFoundException() {
    // Given
    when(sleepLogRepository.findByUserIdAndFromDate(1L, LocalDate.now()))
        .thenReturn(Optional.empty());

    // When & Then
    assertThrows(
        SleepLogNotFoundException.class, () -> findSleepLogService.findLastNightSleepLog(1L));
  }
}
