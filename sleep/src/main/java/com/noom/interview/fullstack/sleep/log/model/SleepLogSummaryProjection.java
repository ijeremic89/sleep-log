package com.noom.interview.fullstack.sleep.log.model;

import java.time.LocalDate;

public interface SleepLogSummaryProjection {
  LocalDate getFromDate();

  LocalDate getToDate();

  Long getAvgTotalTimeInBedSeconds();

  Long getAvgTimeInBedFromSeconds();

  Long getAvgTimeInBedToSeconds();

  Long getGoodMoodCount();

  Long getOkMoodCount();

  Long getBadMoodCount();
}
