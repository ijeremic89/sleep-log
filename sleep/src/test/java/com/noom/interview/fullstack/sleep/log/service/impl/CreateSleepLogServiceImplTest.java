package com.noom.interview.fullstack.sleep.log.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.noom.interview.fullstack.sleep.common.exception.SleepLogAlreadyExistsException;
import com.noom.interview.fullstack.sleep.common.exception.UserNotFoundException;
import com.noom.interview.fullstack.sleep.log.SleepLogRepository;
import com.noom.interview.fullstack.sleep.log.model.Mood;
import com.noom.interview.fullstack.sleep.log.model.SleepLogEntity;
import com.noom.interview.fullstack.sleep.log.model.SleepLogRequest;
import com.noom.interview.fullstack.sleep.log.model.SleepLogResponse;
import com.noom.interview.fullstack.sleep.user.UserEntity;
import com.noom.interview.fullstack.sleep.user.UserRepository;
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
class CreateSleepLogServiceImplTest {

  @Mock private SleepLogRepository sleepLogRepository;

  @Mock private UserRepository userRepository;

  @InjectMocks private CreateSleepLogServiceImpl createSleepLogService;

  private SleepLogRequest validRequest;
  private UserEntity mockUser;
  private SleepLogEntity mockSleepLogEntity;

  @BeforeEach
  void setUp() {
    validRequest =
        new SleepLogRequest(
            LocalTime.of(22, 0), // timeInBedFrom
            LocalTime.of(6, 0), // timeInBedTo
            Mood.GOOD);

    mockUser = new UserEntity();
    mockUser.setId(1L);

    mockSleepLogEntity = new SleepLogEntity();
    mockSleepLogEntity.setUser(mockUser);
    mockSleepLogEntity.setSleepDate(LocalDate.now());
  }

  @Test
  void createLastNightSleepLog_withValidRequest_returnsSuccess() {
    // Given
    when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
    when(sleepLogRepository.findByUserIdAndFromDate(1L, LocalDate.now()))
        .thenReturn(Optional.empty());

    // When
    SleepLogResponse response = createSleepLogService.createLastNightSleepLog(validRequest, 1L);

    // Then
    assertNotNull(response);
    assertEquals(validRequest.timeInBedFrom(), response.timeInBedFrom());
    assertEquals(validRequest.timeInBedTo(), response.timeInBedTo());
  }

  @Test
  void createLastNightSleepLog_withTimeInBedAfterMidnight_returnsSuccess() {
    // Given
    when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
    when(sleepLogRepository.findByUserIdAndFromDate(1L, LocalDate.now()))
        .thenReturn(Optional.empty());

    var sleepLog =
        new SleepLogRequest(
            LocalTime.of(0, 10), // timeInBedFrom
            LocalTime.of(6, 0), // timeInBedTo
            Mood.GOOD);

    // When
    SleepLogResponse response = createSleepLogService.createLastNightSleepLog(sleepLog, 1L);

    // Then
    assertNotNull(response);
    assertEquals(sleepLog.timeInBedFrom(), response.timeInBedFrom());
    assertEquals(sleepLog.timeInBedTo(), response.timeInBedTo());
  }

  @Test
  void createLastNightSleepLog_withNonExistingUser_throwsUserNotFoundException() {
    // Given
    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    // When & Then
    assertThrows(
        UserNotFoundException.class,
        () -> {
          createSleepLogService.createLastNightSleepLog(validRequest, 1L);
        });
  }

  @Test
  void createLastNightSleepLog_withAlreadyExistingSleepLog_throwsSleepLogAlreadyExistsException() {
    // Given
    when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
    when(sleepLogRepository.findByUserIdAndFromDate(1L, LocalDate.now()))
        .thenReturn(Optional.of(mockSleepLogEntity));

    // When & Then
    assertThrows(
        SleepLogAlreadyExistsException.class,
        () -> {
          createSleepLogService.createLastNightSleepLog(validRequest, 1L);
        });
  }

  @Test
  void testCalculateTotalTimeInBed_TimesAreNull() {
    // Given
    SleepLogRequest requestWithNullTimes = new SleepLogRequest(null, null, Mood.GOOD);

    // Mock the userRepository to return a valid user
    UserEntity mockUser = new UserEntity();
    mockUser.setId(1L);
    when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

    // When & Then
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          createSleepLogService.createLastNightSleepLog(requestWithNullTimes, 1L);
        });
  }
}
