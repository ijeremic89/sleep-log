package com.noom.interview.fullstack.sleep.log.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.noom.interview.fullstack.sleep.common.exception.InvalidDaysException;
import com.noom.interview.fullstack.sleep.common.exception.UserNotFoundException;
import com.noom.interview.fullstack.sleep.log.SleepLogRepository;
import com.noom.interview.fullstack.sleep.log.model.*;
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
class SleepLogSummaryServiceImplTest {

  @Mock private SleepLogRepository sleepLogRepository;
  @Mock private UserRepository userRepository;

  @InjectMocks private SleepLogSummaryServiceImpl sleepLogSummaryService;

  private UserEntity mockUser;
  private SleepLogSummaryProjection mockSleepLogSummary;

  @BeforeEach
  void setUp() {
    mockUser = new UserEntity();
    mockUser.setId(1L);
  }

  @Test
  void getLastNDaysSummary_withMultipleSleepLogs_returnsCorrectSummary() {
    // Given
    Long userId = 1L;
    int days = 5;
    LocalDate toDate = LocalDate.now();
    LocalDate fromDate = toDate.minusDays(days);

    SleepLogSummaryProjection mockSummary = mock(SleepLogSummaryProjection.class);
    when(mockSummary.getFromDate()).thenReturn(fromDate);
    when(mockSummary.getToDate()).thenReturn(toDate);
    when(mockSummary.getAvgTimeInBedFromSeconds())
        .thenReturn(50000L); // 13:53:20 for avgTimeInBedFrom
    when(mockSummary.getAvgTimeInBedToSeconds()).thenReturn(86410L); // 00:00:10 for avgTimeInBedTo
    when(mockSummary.getGoodMoodCount()).thenReturn(3L);
    when(mockSummary.getOkMoodCount()).thenReturn(2L);
    when(mockSummary.getBadMoodCount()).thenReturn(1L);

    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
    when(sleepLogRepository.getSleepSummary(eq(userId), any(LocalDate.class), any(LocalDate.class)))
        .thenReturn(mockSummary);

    // When
    SleepLogSummaryResponse response = sleepLogSummaryService.getLastNDaysSummary(userId, days);

    // Then
    assertEquals(LocalTime.of(13, 53, 20), response.avgTimeInBedFrom());
    assertEquals(LocalTime.of(0, 0, 10), response.avgTimeInBedTo());
    assertEquals(3L, response.moodFrequencies().get(Mood.GOOD));
    assertEquals(2L, response.moodFrequencies().get(Mood.OK));
    assertEquals(1L, response.moodFrequencies().get(Mood.BAD));
  }

  @Test
  void getLastNDaysSummary_withNonExistingUser_throwsUserNotFoundException() {
    // Given
    Long userId = 1L;
    Integer days = 5;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    // When & Then
    assertThrows(
        UserNotFoundException.class,
        () -> {
          sleepLogSummaryService.getLastNDaysSummary(userId, days);
        });
  }

  @Test
  void getLastNDaysSummary_withZeroDays_throwsInvalidDaysException() {
    // Given
    Long userId = 1L;
    Integer days = 0;

    when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(mockUser));

    // When & Then
    assertThrows(
        InvalidDaysException.class,
        () -> {
          sleepLogSummaryService.getLastNDaysSummary(userId, days);
        });
  }

  @Test
  void getLastNDaysSummary_withNoSleepLogs_returnsNull() {
    // Given
    Long userId = 1L;
    int days = 5;
    LocalDate toDate = LocalDate.now();
    LocalDate fromDate = toDate.minusDays(days);

    when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
    when(sleepLogRepository.getSleepSummary(userId, fromDate, toDate)).thenReturn(null);

    // When
    SleepLogSummaryResponse response = sleepLogSummaryService.getLastNDaysSummary(userId, days);

    // Then
    assertNull(response);
  }
}
